/*
 * HistoryList.fx
 *
 * Created on 2009/05/21, 14:19:14
 */

package com.xrea.s268.ashphy.calcfx;

import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Caluration History List Component
 * @author ashphy
 */
public class HistoryList extends CustomNode{

	public var width : Number = 200;
	public var height : Number = bind sizeof items * itemHeight;

	public-read var items : HistoryItem[] = [];
	public-read var count: Integer = bind sizeof items;

	public var selectedIndex: Integer = -1 on replace old {
		println("selectedIndex is {selectedIndex}");
		println("old value is {old}");

		if(selectedIndex < -1)selectedIndex = -1;
		if((sizeof items) < selectedIndex)selectedIndex = sizeof items;

		if(0 <= old)items[old].nonSelect();
		if(0 <= selectedIndex)items[selectedIndex].select();
	}

	var background = Rectangle {
		width: bind width
		height: bind height
	};

	var itemHeight = 20;

	/**Select Changed Event*/
	public var selected: function(item: HistoryItem): Void;

	var even : Paint = LinearGradient {
		startX : 0.0
		startY : 0.0
		endX : 0.0
		endY : 1.0
		stops: [
			Stop {
				color : Color.WHITE
				offset: 0.0
			},
			Stop {
				color : Color.WHITESMOKE
				offset: 1.0
			}
		]
	}

	var odd : Paint = LinearGradient {
		startX : 0.0
		startY : 0.0
		endX : 0.0
		endY : 1.0
		stops: [
			Stop {
				color : Color.web("#dcdcdc")
				offset: 0.0
			},
			Stop {
				color : Color.web("#c0c0c0")
				offset: 1.0
			}
		]
	}

	override public function create() : Node {
		return Group {
			translateY: bind this.translateY
			content: bind [
				for (i in [0..sizeof items - 1]) {
					Group {
						translateY: i * itemHeight * 2
						content: [
							Rectangle {
								width: bind width
								height: bind itemHeight * 2
								fill: if((i mod 2) == 0)even else odd
							},
							items[i]
						]
					}
				}
			]
		}
	}

	public function add(formula: String, solution: String): HistoryItem {
		var item = HistoryItem{
			width: bind width
			formula: formula
			solution: solution
			selected: bind selected
		};
		insert item into items;
		return item;
	}
}
