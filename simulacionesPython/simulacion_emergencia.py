import simpy
import random
import matplotlib.pyplot as plt

# Configuraciones generales
RANDOM_SEED = 10
SIM_TIME = 24 * 60  # 24 horas en minutos
INTERVALO_LLEGADA = 10  # minutos entre pacientes
random.seed(RANDOM_SEED)

# Hospital y paciente
class Hospital:
    def __init__(self, env, num_enfermeras, num_doctores, num_rayosx, num_lab):
        self.env = env
        self.enfermeras = simpy.Resource(env, capacity=num_enfermeras)
        self.doctores = simpy.PriorityResource(env, capacity=num_doctores)
        self.rayosX = simpy.PriorityResource(env, capacity=num_rayosx)
        self.laboratorio = simpy.PriorityResource(env, capacity=num_lab)

class Paciente:
    def __init__(self, env, nombre, hospital, tiempo_total, severidades):
        self.env = env
        self.nombre = nombre
        self.hospital = hospital
        self.severidad = random.randint(1, 5)
        self.tiempo_inicio = env.now
        self.tiempo_total = tiempo_total
        self.severidades = severidades

    def proceso(self):
        with self.hospital.enfermeras.request() as req:
            yield req
            yield self.env.timeout(10)  # Triage

        with self.hospital.doctores.request(priority=self.severidad) as req:
            yield req
            yield self.env.timeout(random.randint(15, 20))  # Consulta médica

        if random.random() < 0.5:
            with self.hospital.rayosX.request(priority=self.severidad) as req:
                yield req
                yield self.env.timeout(20)  # Rayos X

        if random.random() < 0.6:
            with self.hospital.laboratorio.request(priority=self.severidad) as req:
                yield req
                yield self.env.timeout(random.randint(30, 45))  # Laboratorio

        self.tiempo_total.append(self.env.now - self.tiempo_inicio)
        self.severidades.append(self.severidad)

# Simulación
def simular(config):
    env = simpy.Environment()
    hospital = Hospital(env, *config)
    tiempo_total = []
    severidades = []

    def llegada_pacientes(env, hospital):
        i = 0
        while True:
            yield env.timeout(random.expovariate(1.0 / INTERVALO_LLEGADA))
            paciente = Paciente(env, f"Paciente-{i}", hospital, tiempo_total, severidades)
            env.process(paciente.proceso())
            i += 1

    env.process(llegada_pacientes(env, hospital))
    env.run(until=SIM_TIME)
    return tiempo_total, severidades

# Configuraciones
nombres_config = [
    "Base",
    "Más enfermeras",
    "Más doctores",
    "Más tecnología",
    "Equilibrado"
]
configuraciones = [
    (2, 3, 1, 1),  # Base
    (4, 3, 1, 1),  # Más enfermeras
    (2, 5, 1, 1),  # Más doctores
    (2, 3, 2, 2),  # Más tecnología
    (3, 4, 2, 2),  # Equilibrado
]

# Ejecutar simulaciones
resultados = []
for config in configuraciones:
    tiempos, severidades = simular(config)
    resultados.append((tiempos, severidades))


# Mostrar resumen
for nombre, (tiempos, _) in zip(nombres_config, resultados):
    print(f"{nombre}: {len(tiempos)} pacientes, tiempo promedio: {sum(tiempos)/len(tiempos):.2f} min")

# Graficar resultados
plt.figure(figsize=(10, 6))
promedios = [sum(t)/len(t) for t, _ in resultados]
plt.bar(nombres_config, promedios, color='cornflowerblue', edgecolor='black')
plt.ylabel("Tiempo promedio por paciente (min)")
plt.title("Comparación de tiempos promedio por configuración")
plt.xticks(rotation=20)
plt.grid(True, axis='y', linestyle='--', alpha=0.7)
plt.tight_layout()
plt.show()