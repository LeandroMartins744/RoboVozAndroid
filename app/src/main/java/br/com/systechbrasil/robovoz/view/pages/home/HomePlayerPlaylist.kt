package br.com.systechbrasil.robovoz.view.pages.home

import myColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.viewModel.PlaylistViewModel


@Composable
fun homePlayerPlaylist(viewModel: PlaylistViewModel, actions: HomeSetPlayerList, onClose:()->Unit) {
    if (viewModel.openDialogPlayer) {
        Dialog(onDismissRequest =  onClose ) {
            Surface(
                modifier = Modifier.height(300.dp).fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.LightGray
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {


                    Row(modifier = Modifier.align(Alignment.TopCenter)) {

                        Image(
                            painter = painterResource(R.drawable.img),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(200.dp)
                                .border(2.0.dp, Color.Gray),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Row(modifier = Modifier.align(Alignment.TopEnd).height(40.dp)) {
                        IconButton(
                            onClick = {
                                onClose()
                            },
                            enabled = true,
                            modifier = Modifier.height(30.dp).width(30.dp)
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(R.drawable.baseline_close_dialog_24),
                                contentDescription = "Close Dialog"
                            )
                        }
                    }
                    Row(modifier = Modifier.align(Alignment.TopStart).padding(20.dp)) {
                        Text(modifier = Modifier.fillMaxWidth(), text = "Playlist ${viewModel.playResponse.name} \nCom ${viewModel.playResponse.audios.count()} audio(s)", color = myColor.orange)
                    }

                    Row(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                modifier = Modifier.height(60.dp).width(60.dp).padding(top = 20.dp),
                                onClick = { actions.onClickPrevious() },
                                enabled = true
                            ) {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    painter = painterResource(R.drawable.baseline_play_previous),
                                    contentDescription = "content description")
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                modifier = Modifier.height(80.dp).width(80.dp),
                                onClick = { actions.onClickPlay() },
                                enabled = true
                            ) {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    painter = painterResource(viewModel.openDialogIcon),
                                    contentDescription = "content description")
                            }

                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                modifier = Modifier.height(60.dp).width(60.dp).padding(top = 20.dp),
                                onClick = { actions.onClickNext() },
                                enabled = true
                            ) {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    painter = painterResource(R.drawable.baseline_play_next),
                                    contentDescription = "content description")
                            }
                        }
                    }
                }
            }
        }
    }
}