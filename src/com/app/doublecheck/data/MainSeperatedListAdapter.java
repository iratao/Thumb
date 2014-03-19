package com.app.doublecheck.data;

import java.util.LinkedHashMap;
import java.util.Map;

import com.app.doublecheck.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

public class MainSeperatedListAdapter extends BaseAdapter{
	
	public final Map<String,Adapter> sections = new LinkedHashMap<String,Adapter>();  
    public final ArrayAdapter<String> headers;  
    public final static int TYPE_SECTION_HEADER = 0;  
    
    public MainSeperatedListAdapter(Context context) {  
        headers = new ArrayAdapter<String>(context, R.layout.main_list_header);  
    }  
    
    public void addSection(String section, Adapter adapter) {  
        this.headers.add(section);  
        this.sections.put(section, adapter);  
    }  

	public int getCount() {
		// TODO Auto-generated method stub
		int total = 0;  
        for(Adapter adapter : this.sections.values())  
            total += adapter.getCount() + 1;  
        return total; 
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		for(Object section : this.sections.keySet()) {  
            Adapter adapter = sections.get(section);  
            int size = adapter.getCount() + 1;  
  
            // check if position inside this section  
            if(position == 0) return section;  
            if(position < size) return adapter.getItem(position - 1);  
  
            // otherwise jump into next section  
            position -= size;  
        }  
        return null; 
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		// assume that headers count as one, then total all sections  
        int total = 1;  
        for(Adapter adapter : this.sections.values())  
            total += adapter.getViewTypeCount();  
        return total;  
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		int type = 1;  
        for(Object section : this.sections.keySet()) {  
            Adapter adapter = sections.get(section);  
            int size = adapter.getCount() + 1;  
  
            // check if position inside this section  
            if(position == 0) return TYPE_SECTION_HEADER;  
            if(position < size) return type + adapter.getItemViewType(position - 1);  
  
            // otherwise jump into next section  
            position -= size;  
            type += adapter.getViewTypeCount();  
        }  
        return -1;  
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		 return false;  
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		 return (getItemViewType(position) != TYPE_SECTION_HEADER);  
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;  
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		int sectionnum = 0;  
        for(Object section : this.sections.keySet()) {  
            Adapter adapter = sections.get(section);  
            int size = adapter.getCount() + 1;  
  
            // check if position inside this section  
            if(position == 0) return headers.getView(sectionnum, convertView, parent);  
            if(position < size) return adapter.getView(position - 1, convertView, parent);  
  
            // otherwise jump into next section  
            position -= size;  
            sectionnum++;  
        }  
        return null;  
	}

}
