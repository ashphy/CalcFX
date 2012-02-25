/*
 * Slider.fx
 *
 * Created on 2009/05/22, 18:01:30
 */

package com.xrea.s268.ashphy.calcfx;

import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Scroll Bar
 * @author ashphy
 */

public class Slider extends CustomNode{

	public var fill : Paint;

	public var width : Number = 20;
	public var height : Number = 500;

	public var max = 10;
	public var value = 0 on replace {
		if(max == 0) value =0
		else if(value < 0) value = 0
		else if(max <= value) value = max-1;

		action(value);
	}
	
	var steps: Number = bind height / max;
	var pos : Number = bind value * steps;

	public var action : function(value: Integer);

	var slider : Node = Rectangle {
		x: 0;
		y: 0;
		arcWidth: 20
		arcHeight: 20
		width: bind width
		height: bind steps
		fill: LinearGradient {
			startX : 0.0
			startY : 0.0
			endX : 1.0
			endY : 0.0
			stops: [
				Stop {
					color : Color.web("#f8f8ff")
					offset: 0.0
				},
				Stop {
					color : Color.web("#b0c4de")
					offset: 1.0
				},

			]
		}
		translateY: bind pos
		onMouseDragged: function(event){
			if(event.primaryButtonDown) {
				value = ((event.dragAnchorY + event.dragY) / steps) as Integer;
			}
		}
	}

	override public function create() :Node {
		return Group {
			onMouseWheelMoved: function( e: MouseEvent ):Void {
				value += e.wheelRotation;
			}
			content : [
				Rectangle {
					width: bind width
					height: bind height
					fill: fill
				},
				slider
			]
		}
	}
}
