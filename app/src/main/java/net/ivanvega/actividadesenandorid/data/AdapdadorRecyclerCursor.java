package net.ivanvega.actividadesenandorid.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapdadorRecyclerCursor  extends RecyclerView.Adapter <AdapdadorRecyclerCursor.ViewHolder> {


    Cursor cursor;
    Context contexto;
    LayoutInflater layoutInflater;

    public AdapdadorRecyclerCursor(Context context, Cursor cursor) {
        this.cursor = cursor;
        this.contexto = context;

        layoutInflater  = (LayoutInflater)
                contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View layoutItem =
                  layoutInflater.inflate(android.R.layout.simple_list_item_2,null);

        return new ViewHolder(layoutItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        this.cursor.moveToPosition(position);

        holder.txtID.setText( String.valueOf(  cursor.getLong(0)));
        holder.txtEMail.setText(cursor.getString(2));

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtID;
        TextView txtEMail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(android.R.id.text1);
            txtEMail = itemView.findViewById(android.R.id.text2);
        }
    }
}
