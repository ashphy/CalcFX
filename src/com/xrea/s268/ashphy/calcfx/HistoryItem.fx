package com.xrea.s268.ashphy.calcfx;

import javafx.scene.CustomNode;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.input.MouseEvent;

/**
 * CalcFX History Item
 * @author ashphy
 */
public class HistoryItem extends CustomNode {

	public-init var formula : String;
	public-init var solution : String;

	public var width : Number = 200;
	public var height : Number = 20;

	public var backgroundColor : Color = Color.TRANSPARENT;

	public var isSelect : Boolean = false;
	public var isHover : Boolean = false;

	var copyButton: Button;
	var back : Node;

	//Select Animation
	var selectAnimation: Timeline;
	var nonSelectAnimation: Timeline;

	public var selected: function(item: HistoryItem):Void;

	init {
		back = Rectangle {
			width: bind width
			height: bind height * 2
			fill : bind Color.web("#e6e6fa")
			opacity: 0

			onMouseEntered: function(event): Void {;
				select();
			}
			onMouseExited: function(event): Void {
				nonSelect();
			}
		};

		copyButton = Button {
			translateX: bind width - 70
			translateY: 10
			width: 60
			height: 20
			text: "Copy"
			onMouseClicked: function( e: MouseEvent ):Void {
				selected(this);
			}
		}

		selectAnimation = Timeline {
			keyFrames : [
				KeyFrame {
					time : 0s
					values: back.opacity => 0
				},
				KeyFrame {
					time: 0.3s
					values: back.opacity => 1
				}

			]
		}

		nonSelectAnimation = Timeline {
			keyFrames : [
				KeyFrame {
					time : 0s
					values: back.opacity => 1
				},
				KeyFrame {
					time: 0.3s
					values: back.opacity => 0
				}

			]
		}
	}

	public function select():Void {
		isSelect = true;
		selectAnimation.playFromStart();
		copyButton.show();
	}

	public function nonSelect(): Void {
		isSelect = false;
		nonSelectAnimation.playFromStart();
		copyButton.hide();
	}

	override public function create(): Node {
		return Group {
			content: [
				back,
				VBox {
					content : [
						Text {
							font : Font {
								size: 12
							}
							translateX: 5
							translateY: 14
							content: bind formula
						},
						Text {
							font : Font {
								size: 18
							}
							translateX: 5
							translateY:20
							content: bind solution
						}
					]
				},
				copyButton
			]
		};
	};
}