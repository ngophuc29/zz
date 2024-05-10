package GiaoDien;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class DisabledRowRenderer extends DefaultTableCellRenderer {
		    public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column) {
		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        
		        // Customize the rendering for disabled rows
		        if (!isSelected) {
		            setForeground(Color.GRAY);
		        }
		        
		        return this;
		    }
		}