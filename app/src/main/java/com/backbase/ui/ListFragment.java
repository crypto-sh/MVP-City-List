package com.backbase.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backbase.City;
import com.backbase.R;
import com.backbase.adapter.CitiesAdapter;
import com.backbase.models.CityInfo;
import com.backbase.onEditTextChange;
import com.backbase.presenter.CityPresenterImpl;
import com.backbase.utils.LogHelper;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements City.View {

    EditText inputText;
    TextView errorText;
    ListView listView;
    ProgressBar progress;

    CityPresenterImpl presenter;

    private String  inputvalue = "";
    private Boolean isDualPage = false;

    LogHelper logHelper = new LogHelper(this.getClass());

    private static String ARG_DUALPAGE  = "dualPage";
    private static String ARG_INPUTTEXT = "inputText";

    private onCitySelectedListener listener;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance(Boolean isDualPage) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_DUALPAGE,isDualPage);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            isDualPage = getArguments().getBoolean(ARG_DUALPAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        inputText = view.findViewById(R.id.inputFilter);
        inputText.addTextChangedListener(new onEditTextChange() {
            @Override
            public void onTextChanged(String value) {
                if (presenter != null) {
                    presenter.filterCitiesInfoRequest(value);
                }
            }
        });
        listView = view.findViewById(R.id.list_view);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            CityInfo item = ((CitiesAdapter) parent.getAdapter()).getItem(position);
            if (listener != null) {
                listener.citySelected(item);
            }
        });
        errorText = view.findViewById(R.id.error_text);
        progress = view.findViewById(R.id.progress);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new CityPresenterImpl(getActivity(), this);
        presenter.getCitiesInfo();
    }

    @Override
    public void citiesInfoList(List<CityInfo> cities) {
        listView.setAdapter(new CitiesAdapter(cities));
        if (isDualPage && cities.size() > 0){
            CityInfo cityInfo = cities.get(0);
            listView.performItemClick(null,0,cityInfo.get_id());
        }
    }

    @Override
    public void showError() {
        errorText.setText(getString(R.string.error_message));
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onCitySelectedListener) {
            listener = (onCitySelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface onCitySelectedListener {
        void citySelected(CityInfo cityInfo);
    }
}
