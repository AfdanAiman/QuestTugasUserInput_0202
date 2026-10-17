package com.example.tugasprak5

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormF1Screen(modifier: Modifier = Modifier) {

    var textNamaDepan by remember { mutableStateOf("") }
    var textNamaBelakang by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var selectedDateText by remember { mutableStateOf("") }
    var selectedExperience by remember { mutableStateOf("") }

    var isSimRacingChecked by remember { mutableStateOf(false) }
    var isPitStopChecked by remember { mutableStateOf(false) }
    var isTelemetryChecked by remember { mutableStateOf(false) }

    var submittedNamaDepan by remember { mutableStateOf("") }
    var submittedNamaBelakang by remember { mutableStateOf("") }
    var submittedEmail by remember { mutableStateOf("") }
    var submittedDate by remember { mutableStateOf("") }
    var submittedExperience by remember { mutableStateOf("") }
    var submittedSkill by remember { mutableStateOf("") }

    var showResult by remember { mutableStateOf(false) }

    var showDatePicker by remember { mutableStateOf(false) }
    var expandedExperience by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    val listExperience = listOf(
        stringResource(R.string.exp_beginner),
        stringResource(R.string.exp_intermediate),
        stringResource(R.string.exp_pro)
    )

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formatter = SimpleDateFormat(
                                "dd/MM/yyyy",
                                Locale.getDefault()
                            )
                            selectedDateText = formatter.format(Date(millis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Batal")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showResult) {
        AlertDialog(
            onDismissRequest = { showResult = false },
            title = {
                Text(
                    text = "Hasil Biodata",
                    color = colorResource(R.color.f1_primary_navy),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Nama: $submittedNamaDepan $submittedNamaBelakang")
                    Text(text = "Email: $submittedEmail")
                    Text(text = "Tgl Lahir: $submittedDate")
                    Text(text = "Experience: $submittedExperience")
                    Text(text = "Skill: $submittedSkill")
                }
            },
            confirmButton = {
                Button(
                    onClick = { showResult = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.f1_primary_navy)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text = "Tutup", color = colorResource(R.color.white))
                }
            },
            shape = RoundedCornerShape(28.dp)
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colorResource(R.color.gray_background)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.header_height))
            ) {
                Image(
                    painter = painterResource(R.drawable.f1),
                    contentDescription = stringResource(R.string.desc_header_bg),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            colorResource(R.color.f1_primary_navy)
                                .copy(alpha = 0.4f)
                        )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        top = dimensionResource(R.dimen.form_top_offset),
                        bottom = 32.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation =
                            dimensionResource(R.dimen.card_elevation)
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.white)
                    ),
                    shape = RoundedCornerShape(
                        topStart =
                            dimensionResource(R.dimen.card_corner_radius),
                        topEnd =
                            dimensionResource(R.dimen.card_corner_radius)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(
                            dimensionResource(R.dimen.padding_large)
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            dimensionResource(R.dimen.padding_small)
                        )
                    ) {

                        Text(
                            text = stringResource(R.string.form_title),
                            style = MaterialTheme.typography.titleLarge,
                            color = colorResource(R.color.f1_primary_navy)
                        )

                        Text(
                            text = stringResource(R.string.form_subtitle),
                            style = MaterialTheme.typography.bodySmall,
                            color = colorResource(R.color.gray_text)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = textNamaDepan,
                                onValueChange = {
                                    textNamaDepan = it
                                },
                                label = {
                                    Text(stringResource(R.string.nama_depan))
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.weight(1f)
                            )

                            OutlinedTextField(
                                value = textNamaBelakang,
                                onValueChange = {
                                    textNamaBelakang = it
                                },
                                label = {
                                    Text(stringResource(R.string.nama_belakang))
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        OutlinedTextField(
                            value = textEmail,
                            onValueChange = {
                                textEmail = it
                            },
                            label = {
                                Text(stringResource(R.string.email))
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        showDatePicker = true
                                    }
                            ) {
                                OutlinedTextField(
                                    value = selectedDateText,
                                    onValueChange = {},
                                    readOnly = true,
                                    enabled = false,
                                    label = {
                                        Text(stringResource(R.string.tgl_lahir))
                                    },
                                    singleLine = true,
                                    shape = RoundedCornerShape(12.dp),
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(
                                                android.R.drawable.ic_menu_my_calendar
                                            ),
                                            contentDescription = stringResource(
                                                R.string.desc_cal_icon
                                            )
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .pointerInput(Unit) {}
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        expandedExperience = true
                                    }
                            ) {
                                OutlinedTextField(
                                    value = selectedExperience,
                                    onValueChange = {},
                                    readOnly = true,
                                    enabled = false,
                                    label = {
                                        Text(
                                            stringResource(R.string.experience)
                                        )
                                    },
                                    singleLine = true,
                                    shape = RoundedCornerShape(12.dp),
                                    trailingIcon = {
                                        Text(
                                            text = if (expandedExperience) {
                                                "▴"
                                            } else {
                                                "▾"
                                            },
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .pointerInput(Unit) {}
                                )

                                DropdownMenu(
                                    expanded = expandedExperience,
                                    onDismissRequest = {
                                        expandedExperience = false
                                    }
                                ) {
                                    listExperience.forEach { option ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(option)
                                            },
                                            onClick = {
                                                selectedExperience = option
                                                expandedExperience = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = stringResource(R.string.pilih_skill),
                            modifier = Modifier.align(Alignment.Start),
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorResource(R.color.black)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isSimRacingChecked,
                                    onCheckedChange = {
                                        isSimRacingChecked = it
                                    }
                                )
                                Text(
                                    text = stringResource(
                                        R.string.skill_sim_racing
                                    ),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isPitStopChecked,
                                    onCheckedChange = {
                                        isPitStopChecked = it
                                    }
                                )
                                Text(
                                    text = stringResource(
                                        R.string.skill_pit_stop
                                    ),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isTelemetryChecked,
                                    onCheckedChange = {
                                        isTelemetryChecked = it
                                    }
                                )
                                Text(
                                    text = stringResource(
                                        R.string.skill_telemetry
                                    ),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                submittedNamaDepan = textNamaDepan
                                submittedNamaBelakang = textNamaBelakang
                                submittedEmail = textEmail
                                submittedDate = selectedDateText
                                submittedExperience = selectedExperience

                                val listSkill = mutableListOf<String>()
                                if (isSimRacingChecked) listSkill.add("Sim Racing")
                                if (isPitStopChecked) listSkill.add("Pit Stop")
                                if (isTelemetryChecked) listSkill.add("Telemetry")
                                submittedSkill = if (listSkill.isNotEmpty()) listSkill.joinToString(", ") else "-"

                                showResult = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(
                                    R.color.f1_accent_red
                                )
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.proceed),
                                color = colorResource(R.color.white),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}