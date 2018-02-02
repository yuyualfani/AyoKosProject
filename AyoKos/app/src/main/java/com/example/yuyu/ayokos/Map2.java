////package com.example.yuyu.ayokos;
////
////import android.app.Activity;
////import android.app.Dialog;
////
////import android.support.v4.app.DialogFragment;
////import android.content.Context;
////import android.content.DialogInterface;
////import android.support.annotation.NonNull;
////import android.support.v4.app.FragmentActivity;
////import android.support.v7.app.AlertDialog;
////import android.support.v7.app.AppCompatActivity;
////import android.os.Bundle;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.widget.EditText;
////import com.example.yuyu.ayokos.model.Kamar;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////
////import java.util.HashMap;
////import java.util.Map;
////
////public class Map2 extends DialogFragment {
////
////    private static final String TAG = Map2.class.getCanonicalName();
////    private Activity activity = null;
////    private OnAddMarker listner;
////
////    public static Map2 getInstance(OnAddMarker listner)
////    {
////        Map2 fragmentDialog = new Map2();
////        fragmentDialog.listner = listner;
////        return fragmentDialog;
////
////    }
////
////    @Override
////    public void onAttach(Activity activity) {
////        super.onAttach(activity);
////        this.activity=activity;
////    }
////
////
////    @Override
////    public Dialog onCreateDialog(Bundle savedInstanceState) {
////
////        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////        builder.setTitle("nama id");
////
////        LayoutInflater inflater = LayoutInflater.from(activity);
////        View view = inflater.inflate(R.layout.activity_map2,null);
////        final EditText  nama = view.findViewById(R.id.nama);
////        final EditText  latitude = view.findViewById(R.id.latitude);
////        final EditText  longitude = view.findViewById(R.id.longitude);
////        builder.setView(view);
////
////        builder.setPositiveButton("kjhgf", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int i) {
////                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("TABLE_KAMAR");
////
////                Kamar kamar = new Kamar();
////                kamar.setNama(nama.getText().toString());
////                kamar.setLatitude(Double.parseDouble(latitude.getText().toString()));
////                kamar.setLongitude(Double.parseDouble(longitude.getText().toString()));
////
////
////                Map<String, Object> childUpdate = new HashMap<>();
////                childUpdate.put(myRef.push().getKey(), kamar.toMap());
////                myRef.updateChildren(childUpdate);
////                listner.onAddMarker();
////            }
////        });
////        return builder.create();
////    }
////
////
////    public interface OnAddMarker {
////        void onAddMarker();
////    }
////
////
////}
////
////
////
////
////
////
////
////
////
////
////
////
//
//
//package com.example.yuyu.ayokos;
//
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.DialogFragment;
//import android.support.v7.app.AlertDialog;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//
//import com.example.yuyu.ayokos.model.Kamar;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by bruno on 9/23/16.
// */
//
//public class Map2 extends DialogFragment
//{
//    private static final String TAG = Map2.class.getCanonicalName();
//    private Activity activity = null;
//    private OnAddMarker listner;
//
//    public static Map2 getInstance(OnAddMarker listner)
//    {
//        Map2 fragmentDialog = new Map2();
//        fragmentDialog.listner = listner;
//        return fragmentDialog;
//    }
//
//    @Override
//    public void onAttach(Activity activity)
//    {
//        super.onAttach(activity);
//        this.activity = activity;
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState)
//    {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        builder.setTitle("Masukkan Nama kos");
//
//        LayoutInflater inflater = LayoutInflater.from(activity);
//        View view = inflater.inflate(R.layout.activity_map2, null);
//        final EditText edtNome = (EditText) view.findViewById(R.id.nama);
//        final EditText edtLatitude = (EditText) view.findViewById(R.id.latitude);
//        final EditText edtLongitude = (EditText) view.findViewById(R.id.longitude);
//
//        builder.setView(view);
//
//        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("TABLE_KAMAR");
//
//                Kamar kamar = new Kamar();
//                kamar.setNama(edtNome.getText().toString());
//                kamar.setLatitude(Double.parseDouble(edtLatitude.getText().toString()));
//                kamar.setLongitude(Double.parseDouble(edtLongitude.getText().toString()));
//
//                Map<String, Object> childUpdates = new HashMap<>();
//                childUpdates.put(myRef.push().getKey(), kamar.toMap());
//                myRef.updateChildren(childUpdates);
//                listner.onAddMarker();
//
//            }
//        });
//        return builder.create();
//
//    }
//    public interface OnAddMarker{
//        void onAddMarker();
//    }
//}
