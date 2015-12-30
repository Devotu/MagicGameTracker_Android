package se.devotu.magicgametracker.gui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.IntegrationManager;
import se.devotu.magicgametracker.enums.Status;

public class ExportImport extends Activity {

    private Button bExport, bImport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportimport);

        bExport = (Button)findViewById(R.id.bExportDatabase);
        bExport.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                IntegrationManager im = new IntegrationManager();
                Status result = im.exportDatabaseToExternalStorage(ExportImport.this);
                Toast resultToast = Toast.makeText(ExportImport.this, getString(R.string.export) + " " + result, Toast.LENGTH_SHORT);
                resultToast.show();
            }
        });

        bImport = (Button)findViewById(R.id.bImportDatabase);
        bImport.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ExportImport.this);
                builder
                        .setTitle(getString(R.string.importword))
                        .setMessage(getString(R.string.importwarning))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNegativeButton(getString(R.string.no), null)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                IntegrationManager im = new IntegrationManager();
                                Status result = im.importDatabaseFromExternalStorage(ExportImport.this);
                                Toast resultToast = Toast.makeText(ExportImport.this, getString(R.string.importword) + " " + result, Toast.LENGTH_SHORT);
                                resultToast.show();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                Intent intent = new Intent(ExportImport.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 11);
                startActivity(intent);
                ExportImport.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


