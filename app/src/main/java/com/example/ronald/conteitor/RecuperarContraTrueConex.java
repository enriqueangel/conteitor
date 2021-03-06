package com.example.ronald.conteitor;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecuperarContraTrueConex.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecuperarContraTrueConex#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RecuperarContraTrueConex extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button BotonRecueprarContra;
    Activity Actividad;
    EditText EDTCorreo;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecuperarContraTrueConex.
     */
    // TODO: Rename and change types and number of parameters
    public static RecuperarContraTrueConex newInstance(String param1, String param2) {
        RecuperarContraTrueConex fragment = new RecuperarContraTrueConex();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public RecuperarContraTrueConex() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public boolean ProbarConex(){

        Actividad = getActivity();
        ConnectivityManager connectivityManager = (ConnectivityManager)Actividad.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()){
            return true;
        }else{
            return false;
        }


    }

    public void MensajeNoConexion(){

        Actividad = getActivity();
        Toast ErrorConexIntern = Toast.makeText(Actividad, "No hay internet ", Toast.LENGTH_SHORT);
        ErrorConexIntern.show();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view  = inflater.inflate(R.layout.fragment_recuperar_contra_true_conex, container, false);


        BotonRecueprarContra = (Button)view.findViewById( R.id.buttonRecuperarContra );
        EDTCorreo = (EditText) getActivity().findViewById( R.id.editTextCorreoRecuperarContra);

        BotonRecueprarContra.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ProbarConex()){


                    /*
                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    String URL = "http://192.168.1.52/ConteitorAPP/consultarusuario.php?Correo="+EDTCorreo.getText().toString();
                    StringRequest stringRequest = new StringRequest( Request.Method.GET, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {




                        }
                    } );

                    queue.add(stringRequest);

                        */
                }else{
                    MensajeNoConexion();
                    RecuperarContraFalseConex Loginfalse = new RecuperarContraFalseConex();
                    FragmentTransaction transicion = getFragmentManager().beginTransaction();
                    transicion.replace(R.id.ContenedorRecuperarContra,Loginfalse);
                    transicion.commit();
                }
            }
        } );

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
