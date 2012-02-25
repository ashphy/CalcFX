/*
 * Main.fx
 *
 * Created on 2009/05/19, 20:25:39
 */

package com.xrea.s268.ashphy.calcfx;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import com.xrea.s268.ashphy.calcfx.HistoryList;

/**
 * CalcFX
 * @author ashphy
 */

class CalcFX extends Scene{
	var expressHeight : Integer = 30;
	var sliderWidth : Integer = 20;

	var historyListHeight: Integer = bind (height - expressHeight) as Integer;
	var historyListOffsetY: Integer = bind ((historyListHeight - 40) / 2.0) as Integer;

	var parser: MathInterpreter = MathInterpreter{};
	var h = bind height on replace {
		resetHistryList();
	}

	//GUI parts
	var historyList : HistoryList;
	var slider : Slider;
	var expressionField : TextBox;

	init {
		historyList = HistoryList {
			width: bind width - sliderWidth
			translateY: historyListOffsetY
			onMouseWheelMoved: function( e: MouseEvent ):Void {
				slider.value += e.wheelRotation
			}
			selected: function(item: HistoryItem):Void {
				expressionField.text = item.formula;
			}
		}

		slider = Slider {
			max: bind historyList.count
			translateX: bind width - sliderWidth
			height: bind height - expressHeight
			action: function(value) : Void {
				resetHistryList();
			}

			fill: LinearGradient {
				startX : 0.0
				startY : 0.0
				endX : 0.0
				endY : 1.0
				stops: [
					Stop {
						color : Color.LIGHTGRAY
						offset: 0.0
					},
					Stop {
						color : Color.GRAY
						offset: 1.0
					},
				]
			}
		}

		expressionField = TextBox {
			height: bind expressHeight
			width: bind width
			translateY: bind height - expressHeight
			text: "Input expression here!"
			onKeyReleased: function( e: KeyEvent ):Void {
				if(e.code == KeyCode.VK_ENTER)
				{
					var formula = (e.node as TextBox).text;
					if(formula != "")
					{
						(e.node as TextBox).text = "";

						var solution = parser.parse(formula);
						historyList.add(formula, solution);
						slider.value = historyList.count - 1;
					}
				}
			}

			//History quote
			onKeyPressed: function( e: KeyEvent ):Void {
				if(e.code == KeyCode.VK_UP) {
					if(historyList.selectedIndex == -1)
						historyList.selectedIndex = historyList.count;

					if(historyList.selectedIndex != 0)
						historyList.selectedIndex--;

					expressionField.text = historyList.items[historyList.selectedIndex].formula;
				}
				else if(e.code == KeyCode.VK_DOWN) {
					if(historyList.selectedIndex != historyList.count)
						historyList.selectedIndex++;

					expressionField.text = historyList.items[historyList.selectedIndex].formula;
				}
				else{
					historyList.selectedIndex = -1;
				}
			}
		};

		content = [
			slider
			historyList
			expressionField
		]
	}

	function resetHistryList(): Void {
		var rate : Number;
		if(slider.value == 0) rate = 0
		else rate = slider.value / (historyList.count as Number);
		historyList.translateY = -rate * historyList.height + historyListOffsetY
	}
}

var calcFX : CalcFX = CalcFX {}

var stage = Stage {
    title: "CalcFX"
    width: 300
    height: 500
    scene: calcFX
}

stage