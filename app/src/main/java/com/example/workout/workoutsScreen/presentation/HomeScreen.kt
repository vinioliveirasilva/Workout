package com.example.workout.workoutsScreen.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.workout.R
import com.example.workout.ui.theme.IconToken
import com.example.workout.workoutsScreen.domain.model.Workout
import com.example.workout.ui.theme.WorkoutTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onWorkoutAction: (Int) -> Unit,
    doOnAddWorkoutAction: () -> Unit,
) {
    LaunchedEffect(true) {
        viewModel.doOnStart()
    }

    Home(
        state = viewModel.state.collectAsState().value,
        doOnHomeAction = viewModel::handleAction,
        onWorkoutAction = onWorkoutAction,
        doOnAddWorkoutAction = doOnAddWorkoutAction,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Home(
    state: HomeState,
    doOnHomeAction: (HomeAction) -> Unit,
    onWorkoutAction: (Int) -> Unit,
    doOnAddWorkoutAction: () -> Unit,
) {
    WorkoutTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Treino de hoje") },
                    actions = { state.topBarIconComposable.composable.invoke() }
                )
            },
            bottomBar = {}
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                state.workouts.fastForEachIndexed { index, workout ->
                    ListItem(
                        modifier = Modifier.clickable { onWorkoutAction(workout.id) },
                        headlineContent = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                text = workout.name,
                            )
                        },
                        leadingContent = {
                            Icon(
                                painterResource(id = IconToken.get(workout.icon)),
                                contentDescription = "Localized description",
                                modifier = Modifier.size(48.dp),
                            )
                        },
                        trailingContent = { state.deleteWorkoutComposable.composable(workout.id) }
                    )

                    if (index != state.workouts.lastIndex) {
                        HorizontalDivider()
                    }
                }

                if (state.shouldShowAddButton) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        onClick = { doOnAddWorkoutAction() }
                    ) {
                        Text("Adicionar novo treino")
                    }
                }
            }
        }
    }
}

