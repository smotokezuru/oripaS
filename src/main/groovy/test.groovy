

import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL
import javax.swing.WindowConstants as WC

// 表示するデータを準備
data = [
        [no:1, title:'データ1']
        , [no:2, title:'データ2']
]

// SwingBuilderを準備
swing = new SwingBuilder()

// 準備したSwingBuilderにtableを追加
table = swing.table() {
    tableModel(list:data) {
        propertyColumn(header:'項番', propertyName:'no')
        propertyColumn(header:'タイトル', propertyName:'title')
    }
}

// SwingBuilder にFrameを追加し、widgetとしてtableを追加する
frame = swing.frame(title:"データ一覧"
        , layout: new BL()
        , defaultCloseOperation:WC.EXIT_ON_CLOSE)
        {
            panel(constraints :BL.NORTH) {
                label(text: 'データ一覧')
            }
            panel(constraints:BL.CENTER) {
                scrollPane {
                    widget(table)
                }
            }
        }


frame.pack()

frame.setVisible(true);