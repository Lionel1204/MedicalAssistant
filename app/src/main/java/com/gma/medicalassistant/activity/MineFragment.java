package com.gma.medicalassistant.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gma.medicalassistant.R;
import com.gma.medicalassistant.utils.MedConst;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MineFragment.OnMineFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnMineFragmentInteractionListener mListener;

    private TextView mPhoneTextView;
    private TextView mEmailTextView;
    private Button mModifyBtn;
    private Button mBindWechatBtn;
    private String TAG = "MineFragment";

    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        mPhoneTextView = view.findViewById(R.id.mine_phone_view);
        mEmailTextView = view.findViewById(R.id.mine_email_view);
        mModifyBtn = view.findViewById(R.id.btn_mine_modify_info);
        mBindWechatBtn = view.findViewById(R.id.btn_mine_add_wechat);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get(ARG_PARAM1).toString();
            //tv.setText(name);
        }

        String[] pieces = MedConst.MOCK_LOGIN_INFO1.split(":");
        String phone = pieces[1];
        String mockEmail = "138xxxxxx@qq.com";

        String showPhoneInfo = view.getResources().getString(R.string.mine_phone_info) + phone;
        SpannableString styledPhoneInfo = new SpannableString(showPhoneInfo);
        styledPhoneInfo.setSpan(new TextAppearanceSpan(this.getContext(), R.style.ShowInfoStyle), 0, showPhoneInfo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mPhoneTextView.setText(styledPhoneInfo, TextView.BufferType.SPANNABLE);

        String showEmailInfo = view.getResources().getString(R.string.mine_email_info) + mockEmail;
        SpannableString styledEmailInfo = new SpannableString(showEmailInfo);
        styledEmailInfo.setSpan(new TextAppearanceSpan(this.getContext(), R.style.ShowInfoStyle), 0, showEmailInfo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mEmailTextView.setText(styledEmailInfo, TextView.BufferType.SPANNABLE);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onMineFragmentInteraction("s");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnMineFragmentInteractionListener) {
            mListener = (OnMineFragmentInteractionListener) context;
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
        // TODO: add the button click event
        final String toastInfo = this.getResources().getString(R.string.mine_modify_info_feedback);

        mModifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMineFragmentInteraction(toastInfo);
            }
        });

        mBindWechatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMineFragmentInteraction(toastInfo);
            }
        });
    }

    public interface OnMineFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMineFragmentInteraction(String s);
    }
}
