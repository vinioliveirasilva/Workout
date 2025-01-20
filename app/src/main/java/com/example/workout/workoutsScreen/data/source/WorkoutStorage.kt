package com.example.workout.workoutsScreen.data.source

import com.example.workout.common.storage.Storage
import com.example.workout.workoutsScreen.domain.model.Exercise
import com.example.workout.workoutsScreen.domain.model.ExerciseStatus
import com.example.workout.workoutsScreen.domain.model.Workout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class WorkoutStorage(
    private val storage: Storage,
    private val gsonProvider: Gson,
) {
    fun getWorkout(): Flow<List<Workout>> = storage.getNullableValue<List<Workout>>(WORKOUT_KEY).map {
        it ?: emptyList()
    }

    fun saveWorkout(workouts: List<Workout>) = flow {
        emit(storage.setValue(WORKOUT_KEY, workouts))
    }

    fun getExercises() = flow {
        var id = 0
        val exerciseMap = mutableMapOf<String, List<Exercise>>()

        exercicios.map { (category, exercises) ->
            exerciseMap[category] = exercises.map { name ->
                Exercise(
                    id = ++id,
                    name = name,
                    icon = "",
                    //status = ExerciseStatus.ToDo
                )
            }
        }

        emit(exerciseMap)
    }

    private val exercicios = mapOf(
        "biceps" to listOf(
            "Curls com halteres",
            "Curls com barra",
            "Curls martelo",
            "Rosca concentrada",
            "Rosca Scott",
            "Rosca com halteres inclinada",
            "Rosca com barra W",
            "Rosca com corda na polia baixa"
        ),
        "triceps" to listOf(
            "Tríceps no banco",
            "Extensão de tríceps em p",
            "Extensão de tríceps deitado",
            "Tríceps coice",
            "Elevação lateral dos braços",
            "Flexão de braço inclinada",
            "Prancha de antebraço",
            "Polia alta com corda"
        ),
        "peitoral" to listOf(
            "Supino reto com barra solta",
            "Voador frontal na máquina",
            "Apoio sobre o solo",
            "Supino inclinado máquina",
            "Supino inclinado barra solta",
            "Crucifixo reto com halteres",
            "Crucifixo inclinado com halteres",
            "Bench press com elástico"
        ),
        "costas" to listOf(
            "PUXADA FRONTAL",
            "REMADA CURVADA",
            "REMADA UNILATERAL",
            "REMADA BAIXA",
            "REMADA CAVALINHO",
            "PULLOVER POLIA",
            "VOADOR",
            "BARRA FIXA"
        ),
        "abdomen" to listOf(
            "Abdominal em ",
            "Abdominal com perna elevad",
            "Abdominal cruzad",
            "Abdominal com elevação do quadril na bol",
            "Abdominal ret",
            "Prancha com bol",
            "Prancha com 2 apoio",
            "Giro russo"
        ),
        "ombros" to listOf(
            "Desenvolvimento de ombro",
            "Elevação lateral",
            "Elevação frontal",
            "Remada alta",
            "Crucifixo inverso",
            "Arnold press",
            "Pulley articulado",
            "Puxada frontal aberta"
        ),
        "gluteos" to listOf(
            "Ponte",
            "Ponte com elevação de perna",
            "Elevação do pé ao teto",
            "Elevação de perna lateral",
            "Agachamento clássico",
            "Agachamento búlgaro",
            "Agachamento lateral",
            "Afundo"
        ),
        "quadriceps" to listOf(
            "Extensão da perna",
            "Elevação do calcanhar sentado",
            "Agachamento",
            "Agachamento em plié"
        ),
        "isquiotibiais" to listOf(
            "Alongamento na parede",
            "Alongamento de pé com inclinação",
            "Postura do cachorro olhando para baixo",
            "Alongamento dos isquiotibiais com afundo",
            "Alongamento com rolo de espuma",
            "Alongamento levando as mãos nos pés",
            "Alongamento dos isquiotibiais sentado"
        ),
        "panturrilhas" to listOf(
            "Flexão plantar unilateral",
            "Elevação sóleo",
            "Caminhada do pato",
            "Agachamento frontal livre",
            "Agachamento sum",
            "Afundo",
            "Agachamento búlgaro",
            "Agachamento com salto"
        ),
    )

    private companion object {
        const val WORKOUT_KEY = "WorkoutRepository.WORKOUT_KEY"
    }
}