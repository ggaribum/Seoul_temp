package onetwopunch.seoulinsangshot.com.seoulinsangshot.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Area_DataManager;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.R;

/**
 * Created by kwakgee on 2017. 9. 16..
 */

public class Fragment_Show_Area extends Fragment implements View.OnClickListener{


    private ImageButton[] imgButton=new ImageButton[25];

    private TextView txt_selected_name;
    private TextView txt_selected_name_en;
    private TextView txt_count_selected_photo;
    private TextView txt_count_selected_look;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_area, container, false);


        imgButton[0]=(ImageButton)rootView.findViewById(R.id.db);
        imgButton[1]=(ImageButton)rootView.findViewById(R.id.ddm);
        imgButton[2]=(ImageButton)rootView.findViewById(R.id.dj);
        imgButton[3]=(ImageButton)rootView.findViewById(R.id.ep);
        imgButton[4]=(ImageButton)rootView.findViewById(R.id.ga);
        imgButton[5]=(ImageButton)rootView.findViewById(R.id.gb);
        imgButton[6]=(ImageButton)rootView.findViewById(R.id.gc);
        imgButton[7]=(ImageButton)rootView.findViewById(R.id.gd);
        imgButton[8]=(ImageButton)rootView.findViewById(R.id.gj);
        imgButton[9]=(ImageButton)rootView.findViewById(R.id.gn);
        imgButton[10]=(ImageButton)rootView.findViewById(R.id.gr);
        imgButton[11]=(ImageButton)rootView.findViewById(R.id.gs);
        imgButton[12]=(ImageButton)rootView.findViewById(R.id.jg);
        imgButton[13]=(ImageButton)rootView.findViewById(R.id.jr);
        imgButton[14]=(ImageButton)rootView.findViewById(R.id.jrg);
        imgButton[15]=(ImageButton)rootView.findViewById(R.id.mp);
        imgButton[16]=(ImageButton)rootView.findViewById(R.id.nw);
        imgButton[17]=(ImageButton)rootView.findViewById(R.id.sb);
        imgButton[18]=(ImageButton)rootView.findViewById(R.id.sc);
        imgButton[19]=(ImageButton)rootView.findViewById(R.id.sd);
        imgButton[20]=(ImageButton)rootView.findViewById(R.id.sdm);
        imgButton[21]=(ImageButton)rootView.findViewById(R.id.sp);
        imgButton[22]=(ImageButton)rootView.findViewById(R.id.yc);
        imgButton[23]=(ImageButton)rootView.findViewById(R.id.ydp);
        imgButton[24]=(ImageButton)rootView.findViewById(R.id.ys);

        txt_selected_name=(TextView)rootView.findViewById(R.id.txt_selected_name);
        txt_selected_name_en=(TextView)rootView.findViewById(R.id.txt_selected_name_en);
        txt_count_selected_photo=(TextView)rootView.findViewById(R.id.txt_count_selected_photo);
        txt_count_selected_look=(TextView)rootView.findViewById(R.id.txt_count_selected_look);

        for(int i=0;i<25;i++){
            imgButton[i].setTag(i);
            imgButton[i].setOnClickListener(this);
        }


        return rootView;
    }

    @Override
    public void onClick(View v) {

        ImageButton newButton = (ImageButton) v;

        for (ImageButton tempButton : imgButton) {
            if (tempButton == newButton) {
                int position = v.getId();

                String temp = getResources().getResourceName(position);

                String[] splitStr = temp.split("/");

                Toast.makeText(getContext(),splitStr[1],Toast.LENGTH_LONG).show();

                for(int i = 0; i< Area_DataManager.districtVOs.size(); i++){

                    if(Area_DataManager.districtVOs.get(i).getArea().equals(splitStr[1])) {

                        txt_selected_name.setText((i+1)+". "+Area_DataManager.districtVOs.get(i).getKarea());
                        txt_selected_name_en.setText(Area_DataManager.districtVOs.get(i).getEarea());
                        txt_count_selected_photo.setText(Area_DataManager.districtVOs.get(i).getImagecount() + "");

                    }
                }


            }
        }
    }
}
