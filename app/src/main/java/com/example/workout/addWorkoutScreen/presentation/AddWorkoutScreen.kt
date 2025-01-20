package com.example.workout.addWorkoutScreen.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workout.R
import com.example.workout.ui.theme.WorkoutTheme
import com.example.workout.workoutsScreen.domain.model.Exercise
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun PreviewAddWorkoutScreen() {
    AddWorkout(
        state = AddWorkoutState(),
        onSaveWorkout = { },
        doOnCheckboxChange = { }
    )
}

@Composable
fun AddWorkoutScreen(
    viewModel: AddWorkoutViewModel = koinViewModel(),
    onSaveWorkout: () -> Unit
) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        scope.launch {
            viewModel.event.collect {
                onSaveWorkout()
            }
        }
    }

    AddWorkout(
        viewModel.state.collectAsState().value,
        viewModel::onSaveWorkout,
        viewModel::onCheckboxChange
    )
}

@Composable
private fun AddWorkout(
    state: AddWorkoutState,
    onSaveWorkout: (String) -> Unit,
    doOnCheckboxChange: (Exercise) -> Unit
) {
    WorkoutTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            var name by remember { mutableStateOf("") }


            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                placeholder = { Text("Nome do treino") },
                onValueChange = { name = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            val options = state.exercises.keys
            var selectedLabel by remember { mutableStateOf(options.first()) }
            val scrollState = rememberScrollState()

            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.horizontalScroll(scrollState)
            ) {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        onClick = { selectedLabel = label },
                        selected = label == selectedLabel
                    ) {
                        Text(label)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            val exerciseList = state.exercises[selectedLabel]

            exerciseList?.forEachIndexed { index, exercise ->
                ListItem(
                    modifier = Modifier
                        .height(70.dp)
                        .clickable { doOnCheckboxChange(exercise) },
                    headlineContent = { Text(exercise.name) },
                    leadingContent = {
                        Icon(
                            modifier = Modifier
                                .clickable { }
                                .padding(16.dp),
                            painter = painterResource(id = R.drawable.play_screen),
                            contentDescription = "Localized description",
                        )
                    },
                    trailingContent = {
                        Checkbox(
                            checked = state.selectedExercises.contains(exercise),
                            onCheckedChange = { doOnCheckboxChange(exercise) }
                        )
                    }
                )
                if (index != exerciseList.lastIndex) {
                    HorizontalDivider()
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onSaveWorkout(name) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Salvar")
            }
        }
    }
}