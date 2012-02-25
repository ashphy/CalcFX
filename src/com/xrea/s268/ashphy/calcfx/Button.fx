/*
 * Button.fx
 *
 * Created on 2009/05/27, 16:02:27
 */

package com.xrea.s268.ashphy.calcfx;

import javafx.scene.shape.Rectangle;
import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.paint.Paint;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextOrigin;

/**
 * Animation Button
 * @author ashphy
 */

public class Button extends CustomNode{

	public var text: String = "";
	public var width: Number;
	public var height: Number;

	public var isClick: Boolean = false;

	var button: Rectangle;
	var label: Text;

	var showAnimetion: Timeline;
	var hideAnimation: Timeline;

	/**General State fill*/
	var general: Paint;
	/**Clicked State fill*/
	var click: Paint;

	/**Click Event*/
	public var action: function(): Void;

	init {
		opacity = 0;

		general =  LinearGradient {
			startX: 0.0
			startY: 0.0
			endX : 0.0
			endY : 1.0
			stops: [
				Stop {
					color : Color.WHITE
					offset: 0.0
				},
				Stop {
					color : Color.web("#f0f8ff")
					offset: 1.0
				}
			]
		}

		click = LinearGradient {
			startX: 0.0
			startY: 0.0
			endX : 0.0
			endY : 1.0
			stops: [
				Stop {
					color : Color.web("#b0c4de")
					offset: 0.0
				},
				Stop {
					color : Color.WHITE
					offset: 1.0
				}
			]
		}

		button = Rectangle {
			width: bind width
			height: bind height
			arcWidth: 10
			arcHeight: 10
			stroke: Color.web("#4169e1")
			fill: bind if(isClick)click else general;
			onMousePressed: function( e: MouseEvent ):Void {
				isClick = true;
			}
			onMouseReleased: function( e: MouseEvent ):Void {
				isClick = false;
				action()
			}
		}
		
		label = Text {
			textOrigin: TextOrigin.TOP;
			translateY: bind (height - label.layoutBounds.height) / 2.0 + 2
			translateX: bind (width - label.layoutBounds.width) / 2.0
			content: bind text
		}

		showAnimetion = Timeline {
			keyFrames: [
				KeyFrame {
					time: 0s
					values: opacity => 0
				},
				KeyFrame {
					time: 0.5s
					values: opacity => 1
				}
			]
		}

		hideAnimation = Timeline {
			keyFrames: [
				KeyFrame {
					time: 0s
					values: opacity => 1
				},
				KeyFrame {
					time: 0.5s
					values: opacity => 0
				}
			]
		}
	}

	/**Show Button*/
	public function show(): Void {
		showAnimetion.playFromStart();
	}

	public function hide(): Void {
		hideAnimation.playFromStart();
	}

	override public function create(): Node {
		return Group {
			content: bind [
				button
				label
			]
		}
	}
}
