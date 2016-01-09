package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import se.devotu.magicgametracker.BuildConfig;
import se.devotu.magicgametracker.R;

/**
 * Created by Devotu on 2015-02-07.
 */
public class AboutAppFragment extends Fragment {

    private TextView tvVersionNumber;
    private String t1, t2, t3;
    private TextView tv1,tv2, tv3;
    private TextView tvSubjectTitle, tvBodyTitle;
    private EditText etSubject, etBody;
    private Button bEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_aboutapp, container, false);

        tvVersionNumber = (TextView)v.findViewById(R.id.tvVersionNumber);
        tvVersionNumber.setText(BuildConfig.VERSION_NAME);

        tv1 = (TextView)v.findViewById(R.id.tvAbout1);
        tv1.setText(t1);
        tv2 = (TextView)v.findViewById(R.id.tvAbout2);
        tv2.setText(t2);
        tv3 = (TextView)v.findViewById(R.id.tvAbout3);
        tv3.setText(t3);

        tvSubjectTitle = (TextView)v.findViewById(R.id.tvEmailSubjectTitle);
        tvSubjectTitle.setVisibility(View.INVISIBLE);
        etSubject = (EditText)v.findViewById(R.id.etEmailSubject);
        etSubject.setVisibility(View.INVISIBLE);
        tvBodyTitle = (TextView)v.findViewById(R.id.tvEmailBodyTitle);
        tvBodyTitle.setVisibility(View.INVISIBLE);
        etBody = (EditText)v.findViewById(R.id.etEmailBody);
        etBody.setVisibility(View.INVISIBLE);
        bEmail = (Button)v.findViewById(R.id.bEmail);

        bEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(tvSubjectTitle);
                toggleVisibility(etSubject);
                toggleVisibility(tvBodyTitle);
                toggleVisibility(etBody);
                toggleVisibility(tv1);
                toggleVisibility(tv2);
                toggleVisibility(tv3);
                if (etBody.getVisibility() == View.VISIBLE){
                    bEmail.setText(getString(R.string.send));
                } else {
                    bEmail.setText(getString(R.string.email));
                    sendEmail(etSubject.getText().toString(), etBody.getText().toString() +
                            "\nI'm using Magic Game Tracker for Android version " + BuildConfig.VERSION_NAME);
                    etSubject.setText("");
                    etBody.setText("");
                }
            }
        });

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void updateTextNr(int textToUpdate, String newText){
        switch (textToUpdate){
            case 1: t1 = newText;
                break;
            case 2: t2 = newText;
                break;
            case 3: t3 = newText;
                break;
            default:
                break;
        }
    }

    private void toggleVisibility(View v){
            if (v.getVisibility() == View.VISIBLE) {
                v.setVisibility(View.INVISIBLE);
            } else {
                v.setVisibility(View.VISIBLE);
            }
    }

    private void sendEmail(String subject, String body){
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        email.setType("message/rfc822");

        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"devotu.developer@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(Intent.createChooser(email, "Choose an email client from..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "No email client installed.", Toast.LENGTH_LONG).show();
        }
    }
}