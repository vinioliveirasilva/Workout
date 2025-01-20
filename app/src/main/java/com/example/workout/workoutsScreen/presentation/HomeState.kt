package com.example.workout.workoutsScreen.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.workout.workoutsScreen.domain.model.Workout

data class HomeState(
    val workouts: List<Workout>,
    val topBarIconComposable: EditWorkoutComposable,
    val shouldShowAddButton: Boolean,
    val deleteWorkoutComposable: DeleteWorkoutComposable
)

sealed class EditWorkoutComposable(val composable: @Composable () -> Unit = {}) {
    data class Idle(val onStartEditingAction: () -> Unit) : EditWorkoutComposable(
        composable = {
            IconButton(onClick = { onStartEditingAction() }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null
                )
            }
        }
    )
    data class Editing(val onDoneEditingAction: () -> Unit) : EditWorkoutComposable(
        composable = {
            IconButton(onClick = { onDoneEditingAction() }) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null
                )
            }
        }
    )
}

sealed class DeleteWorkoutComposable(val composable: @Composable (Int) -> Unit = {}) {
    data object Idle : DeleteWorkoutComposable()
    data class Delete(val doOnDelete: (Int) -> Unit) : DeleteWorkoutComposable(
        composable = { id ->
            IconButton(onClick = { doOnDelete(id) }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null
                )
            }
        }
    )
}