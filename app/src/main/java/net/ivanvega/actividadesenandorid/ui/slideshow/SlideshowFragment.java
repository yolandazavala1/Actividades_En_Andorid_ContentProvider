package net.ivanvega.actividadesenandorid.ui.slideshow;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.ivanvega.actividadesenandorid.R;
import net.ivanvega.actividadesenandorid.data.AdapdadorRecyclerCursor;
import net.ivanvega.actividadesenandorid.data.DAOUsuarios;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        layoutManager = new GridLayoutManager(getContext(), 2);

        recyclerView = root.findViewById(R.id.recyclerUsuarios);

        recyclerView.setLayoutManager(layoutManager);

        DAOUsuarios daoUsuarios = new DAOUsuarios(getContext());

        Cursor cursor = daoUsuarios.getAllCursor();

        AdapdadorRecyclerCursor adapdadorRecyclerCursor =
                new AdapdadorRecyclerCursor(
                        getContext(),
                        cursor
                );


        recyclerView.setAdapter(adapdadorRecyclerCursor);






        return root;

    }
}