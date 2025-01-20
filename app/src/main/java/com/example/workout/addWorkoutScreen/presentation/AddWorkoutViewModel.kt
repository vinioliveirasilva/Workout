package com.example.workout.addWorkoutScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.workoutsScreen.domain.model.Exercise
import com.example.workout.workoutsScreen.domain.model.Workout
import com.example.workout.workoutsScreen.domain.repository.WorkoutRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddWorkoutState(
    val exercises: Map<String, List<Exercise>> = emptyMap(),
    val selectedExercises: List<Exercise> = emptyList()
)

sealed interface AddWorkoutEvent {
    data object Close : AddWorkoutEvent
}

class AddWorkoutViewModel(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddWorkoutState())
    val state = _state.asStateFlow()

    private val channel = Channel<AddWorkoutEvent>()
    val event = channel.receiveAsFlow()

    init {
        viewModelScope.launch {
            workoutRepository.getExercises().collect { exercises ->
                _state.update { it.copy(exercises = exercises) }
            }
        }
    }

    fun onSaveWorkout(name: String) {
        viewModelScope.launch {
            workoutRepository.addWorkout(name, _state.value.selectedExercises).collect {
                channel.send(AddWorkoutEvent.Close)
            }
        }
    }

    fun onCheckboxChange(exercise: Exercise) {
        val newList = _state.value.selectedExercises.toMutableList()
        if(newList.find { it.id == exercise.id } != null) {
            newList.remove(exercise)
        } else {
            newList.add(exercise)
        }
        _state.update { it.copy(selectedExercises = newList) }
    }
}