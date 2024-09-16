package com.example.targetlog.main_activity.screens.analytics

 import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
 import co.yml.charts.axis.AxisData
 import co.yml.charts.common.model.Point
 import co.yml.charts.ui.linechart.LineChart
 import co.yml.charts.ui.linechart.model.GridLines
 import co.yml.charts.ui.linechart.model.IntersectionPoint
 import co.yml.charts.ui.linechart.model.Line
 import co.yml.charts.ui.linechart.model.LineChartData
 import co.yml.charts.ui.linechart.model.LinePlotData
 import co.yml.charts.ui.linechart.model.LineStyle
 import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
 import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
 import co.yml.charts.ui.linechart.model.ShadowUnderLine
 import com.example.targetlog.main_activity.screens.common_components.BarChart
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.training_activity.common_component.LeftRightDropdownMenu
import com.example.targetlog.ui.theme.DarkLight
import com.example.targetlog.ui.theme.GreenLight
import com.example.targetlog.ui.theme.Yellow900

@Preview
@Composable
fun AnalyticScreen(
    onClickGotoBluetoothScreen:(String)->Unit={ _ -> },
    onBackClickNavigate:()->Unit={}
){

    Scaffold(
        topBar= {
            TopBar(
                title = "SHOOTING ANALYTICS",
                onBluetoothButtonClick = onClickGotoBluetoothScreen,
                backNavigate  = true,
                onBackClickNavigate  = onBackClickNavigate
            ) },
        containerColor = Color(34, 48, 58, 255)
    ) { innerPadding->
        val configuration = LocalConfiguration.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "AVERAGE SPEED",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Yellow900
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .background(DarkLight, RoundedCornerShape(8.dp))
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //drop box
                var trainingHand by remember {
                    mutableStateOf("Right")
                }

                Text(
                    text = trainingHand,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                LeftRightDropdownMenu { trainingHand = it }
            }
            Spacer(modifier = Modifier.height(20.dp))



            //line graph
            val pointsData: List<Point> =
                listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))

            val steps = 5

            val xAxisData = AxisData.Builder()
                .axisStepSize(100.dp)
                .backgroundColor(Color.Transparent)
                .steps(pointsData.size - 1)
                .labelData { i -> i.toString() }
                .labelAndAxisLinePadding(0.dp)
                .axisLabelColor(Color.White)
                .axisLineColor(Color.White)
                .build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .backgroundColor(Color.Transparent)
                .labelAndAxisLinePadding(0.dp)
                .labelData(
                    labelData = { i ->
                        val yScale = 200 / steps

                        "${i*yScale}"
                        //(i * yScale)  .formatToSinglePrecision()
                    }
                )
                .axisLabelColor(Color.White)
                .axisLineColor(Color.White)
                .build()

            val lineChartData = LineChartData(
                linePlotData = LinePlotData(
                    lines = listOf(
                        Line(
                            dataPoints = pointsData,
                            LineStyle(),
                            IntersectionPoint(),
                            SelectionHighlightPoint(),
                            ShadowUnderLine(),
                            SelectionHighlightPopUp()
                        )
                    ),
                ),
                xAxisData = xAxisData,
                yAxisData = yAxisData,
                gridLines = GridLines(),
                backgroundColor = Color.Transparent
            )

            LineChart(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .height(250.dp),
                lineChartData = lineChartData
            )


            Spacer(modifier = Modifier.height(20.dp))
            Text(
                textAlign = TextAlign.Center,
                text = "WALLBALL REPS",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = GreenLight
            )

            Box (
                modifier = Modifier
            ){
                BarChart(
                    data = mapOf(

                        Pair(290f,"Apr"),
                        Pair(140f,"May"),
                        Pair(210f,"Jun"),
                        Pair(110f,"Jul"),
                        Pair(300f,"Aug"),

                        ),
                    maxValue = 400
                )
            }
        }


    }



}
