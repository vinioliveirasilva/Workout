package com.example.workout.workoutsScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.workoutsScreen.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        HomeState(
            workouts = emptyList(),
            topBarIconComposable = EditWorkoutComposable.Idle { handleAction(HomeAction.StartEdit) },
            shouldShowAddButton = true,
            deleteWorkoutComposable = DeleteWorkoutComposable.Idle
        )
    )
    val state = _state.asStateFlow()

    fun doOnStart() {
        viewModelScope.launch {
            workoutRepository.getWorkouts().collect { result ->
                _state.update {
                    it.copy(
                        workouts = result
                    )
                }
            }
        }
    }

    fun handleAction(action: HomeAction) = when (action) {
        HomeAction.CancelEdit -> {}
        HomeAction.EndEdit -> onEndEdit()
        HomeAction.StartEdit -> onStartEdit()
    }

    private fun onEndEdit() {
        _state.update {
            it.copy(
                topBarIconComposable = EditWorkoutComposable.Idle { handleAction(HomeAction.StartEdit) },
                shouldShowAddButton = true,
                deleteWorkoutComposable = DeleteWorkoutComposable.Idle
            )
        }
        viewModelScope.launch { workoutRepository.saveWorkouts(state.value.workouts).collect() }
    }

    private fun onStartEdit() {
        _state.update {
            it.copy(
                topBarIconComposable = EditWorkoutComposable.Editing { handleAction(HomeAction.EndEdit) },
                shouldShowAddButton = false,
                deleteWorkoutComposable = DeleteWorkoutComposable.Delete { id -> doOnDelete(id) }
            )
        }
    }

    private fun doOnDelete(id: Int) {
        _state.update {
            it.copy(
                workouts = it.workouts.filterNot { workout -> workout.id == id }
            )
        }
    }
}