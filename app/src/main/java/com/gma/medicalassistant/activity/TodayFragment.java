package com.gma.medicalassistant.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.gma.medicalassistant.R;
import com.gma.medicalassistant.utils.MedConst;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodayFragment.OnTodayFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String TAG = "TodayFragment";

    private OnTodayFragmentInteractionListener mListener;

    private ImageButton mCallBtn;
    private Button mMeasureBtn;
    private Button mPlanBtn;
    private Button mResultBtn;
    private Button mHeartRateBtn;

    public TodayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        mCallBtn = view.findViewById(R.id.img_btn_call);
        mMeasureBtn = view.findViewById(R.id.btn_mesure);
        mPlanBtn = view.findViewById(R.id.btn_today_plan);
        mResultBtn = view.findViewById(R.id.btn_today_result);
        mHeartRateBtn = view.findViewById(R.id.btn_today_heart_rate);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get(ARG_PARAM1).toString();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onCallBtnClick(Uri uri) {
        Log.d(TAG, "pressed");
        if (mListener != null) {
            mListener.onTodayFragmentInteraction("test", 0);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnTodayFragmentInteractionListener) {
            mListener = (OnTodayFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTodayFragmentInteraction(
                        MedConst.INTENT_ACTION_CALL_DOCTOR,
                        MedConst.CALL_DOCTOR_REQUEST_CODE);
            }
        });

        mMeasureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTodayFragmentInteraction(
                        MedConst.INTENT_ACTION_MEASUREMENT,
                        MedConst.MEASUREMENT_REQUEST_CODE);
            }
        });

        mPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTodayFragmentInteraction(
                        MedConst.INTENT_ACTION_PLAN,
                        MedConst.PLAN_REQUEST_CODE);
            }
        });

        mResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTodayFragmentInteraction(
                        MedConst.INTENT_ACTION_CHECK_RESULT,
                        MedConst.CHECK_REQUEST_CODE);
            }
        });

        mHeartRateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTodayFragmentInteraction(
                        MedConst.INTENT_ACTION_HEART_RATE,
                        MedConst.HEART_RATE_REQUEST_CODE);
            }
        });
    }

    public interface OnTodayFragmentInteractionListener {
        // TODO: Update argument type and name
        void onTodayFragmentInteraction(String it, int code);
    }
}
