package com.example.workout.exercises.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.workout.exercises.domain.model.ExerciseDetails
import com.example.workout.ui.theme.WorkoutTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

val mock = listOf(
    ExerciseDetails(
        id = 0,
        name = "Agachamento",
        repetitions = 4,
        restTime = 60,
        load = 60.0
    ),
    ExerciseDetails(
        id = 1,
        name = "Agachamento",
        repetitions = 4,
        restTime = 60,
        load = 60.0
    ),
    ExerciseDetails(
        id = 2,
        name = "Agachamento",
        repetitions = 4,
        restTime = 60,
        load = 60.0
    ),
    ExerciseDetails(
        id = 3,
        name = "Agachamento",
        repetitions = 4,
        restTime = 60,
        load = 60.0
    ),
    ExerciseDetails(
        id = 4,
        name = "Agachamento",
        repetitions = 4,
        restTime = 60,
        load = 60.0
    ),
)

@Preview
@Composable
fun PreviewExercises() {
    Exercises(
        exercises = mock,
        doOnExerciseAction = {},
        doOnExerciseNavigate = {}
    )
}

@Composable
fun ExerciseScreen(
    id: Int,
    viewModel: ExerciseViewModel = koinViewModel { parametersOf(id) },
    doOnExerciseNavigate: (Int) -> Unit
) {
    LaunchedEffect(true) {
        viewModel.doOnStart()
    }

    Exercises(
        exercises = viewModel.state.collectAsState().value,
        doOnExerciseAction = viewModel::handleAction,
        doOnExerciseNavigate = doOnExerciseNavigate
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Exercises(
    exercises: List<ExerciseDetails>,
    doOnExerciseAction: (ExerciseAction) -> Unit,
    doOnExerciseNavigate: (Int) -> Unit
) {
    WorkoutTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Exercicios") },
                    actions = {
                        IconButton(onClick = { doOnExerciseAction(ExerciseAction.StartEdit) }) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null
                            )
                        }
                    }
                )
            },
            bottomBar = {}
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                exercises.fastForEachIndexed { index, exercise ->
                    ListItem(
                        modifier = Modifier.clickable { doOnExerciseNavigate(exercise.id) },
                        headlineContent = {
                            Column {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp),
                                    text = exercise.name,
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp),
                                    text = "${exercise.repetitions} repetições",
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp),
                                    text = "carga = ${exercise.load}",
                                )
                            }
                        },
                    )

                    if (index != exercises.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

