package com.example.taniya.tic_tac_toe;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ImageButton[][] buttons = new ImageButton[3][3];
    int turn = 1;
    TextView tv1;
    TextView tv2;
    private final int circle = 1;
    private final int cross = 2;
    private final int empty = 0;
    FloatingActionButton fb;
    final int[][] matrix = new int[3][3];
    private ImageButton[] line = null;
    boolean winner = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[0][0] = findViewById(R.id.topleft);
        buttons[0][1] = findViewById(R.id.midup);
        buttons[0][2] = findViewById(R.id.topright);
        buttons[1][0] = findViewById(R.id.midleft);
        buttons[1][1] = findViewById(R.id.centre);
        buttons[1][2] = findViewById(R.id.midright);
        buttons[2][0] = findViewById(R.id.bottomleft);
        buttons[2][1] = findViewById(R.id.middown);
        buttons[2][2] = findViewById(R.id.bottomright);
        tv1 = findViewById(R.id.turn);
        tv2 = findViewById(R.id.notturn);
        fb = findViewById(R.id.fb);



        for (int i =0;i<3;i++){
            for (int j =0;j<3;j++){
                matrix[i][j] = 0;
            }
        }
        initialise();
        View.OnClickListener list = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialise();
            }
        };

        View.OnClickListener listener = new View.OnClickListener() {
            int i,j;
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {


                v = (ImageButton)v;

                for (int x = 0;x<3;x++){
                    for(int y = 0;y < 3;y++){
                        if(v.equals(buttons[x][y])){
                            i = x;
                            j = y;
                        }
                    }
                }

                if(matrix[i][j] == empty){
                    if(turn == 1){
                        ((ImageButton) v).setImageResource(R.drawable.x3);
                        matrix[i][j] = cross;
                    }else {
                        ((ImageButton) v).setImageResource(R.drawable.o2);
                        matrix[i][j] = circle;
                    }
                    checkwinner();
                    switch (turn) {
                        case 1:
                            tv1.setText("Your Turn");
                            tv2.setText("");
                            turn++;
                            break;
                        case 2:
                            tv2.setText("Your Turn");
                            tv1.setText("");
                            turn--;
                            break;
                    }


                }



            }
        };

        for (int i =0;i<3;i++){
            for (int j =0;j<3;j++){
                buttons[i][j].setOnClickListener(listener);
            }
        }
        fb.setOnClickListener(list);

    }
    private void initialise(){
        tv1.setText("Your Turn");
        tv2.setText("");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = empty;
                buttons[i][j].setImageResource(0);
            }
        }

        for (int i =0;i<3;i++){
            for (int j = 0;j<3;j++){
                buttons[i][j].setImageResource(android.R.color.transparent);
                buttons[i][j].setEnabled(true);
            }
        }

        if(line != null){
            for (int i = 0;i < 3;i++){
                line[i].setBackgroundResource(R.drawable.square_background);
            }
            line = null;
        }




//        buttons[0][0].setBackgroundResource(R.drawable.square_background);
//        buttons[0][1].setBackgroundResource(R.drawable.square_background);
//        buttons[0][2].setBackgroundResource(R.drawable.square_background);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void checkwinner(){
        int symbol;
        if(turn == 1){
            symbol = cross;
        }else{
            symbol = circle;
        }


        //check rows
        for(int i=0;i<3;i++){
            if (matrix[i][0] == symbol && matrix[i][1] == symbol && matrix[i][2] == symbol){
                line = new ImageButton[3];
                line[0] = buttons[i][0];
                line[1] = buttons[i][1];
                line[2] = buttons[i][2];
                break;
            }
        }

        //columns
        for(int i=0;i<3;i++){
            if (matrix[0][i] == symbol && matrix[1][i] == symbol && matrix[2][i] == symbol){
                line = new ImageButton[3];
                line[0] = buttons[0][i];
                line[1] = buttons[1][i];
                line[2] = buttons[2][i];
                break;
            }
        }

        if (matrix[0][0] == symbol && matrix[1][1] == symbol && matrix[2][2] == symbol){
                line = new ImageButton[3];
                line[0] = buttons[0][0];
                line[1] = buttons[1][1];
                line[2] = buttons[2][2];
        }

        else if (matrix[0][2] == symbol && matrix[1][1] == symbol && matrix[2][0] == symbol){
            line = new ImageButton[3];
            line[0] = buttons[0][2];
            line[1] = buttons[1][1];
            line[2] = buttons[2][0];
        }

        else if(line == null){

            if(checkEmptySquare() == false){
                Draw();

            }
//            int i = 0,j = 0;
//            for(;i<3;i++){
//                for(;j<3;j++){
//                    if(matrix[i][j] == 0){
//                        break;
//                    }
//                }
//            }
//            if(i == 3 && j == 3){
//                Draw();
//            }
        }



        changecolor(symbol);





//        if(matrix[0][0] == symbol && matrix[0][1] == symbol && matrix[0][2] == symbol){
//            buttons[0][0].setBackgroundResource(R.drawable.winner);
//            buttons[0][1].setBackgroundResource(R.drawable.winner);
//            buttons[0][2].setBackgroundResource(R.drawable.winner);
//        }
    }

    private boolean checkEmptySquare(){
        boolean flag = false;
        for(int i = 0; i < 3; i++){
            for(int j = 0;j < 3;j++){
                if(matrix[i][j] == 0){
                    flag = true;
                }
            }
        }
        return flag;
    }

    private void Draw() {
        Toast.makeText(MainActivity.this, "Draw",Toast.LENGTH_SHORT).show();

    }

    private void changecolor(int symbol){
        if(line != null){
            if(symbol == cross){
                Toast.makeText(MainActivity.this,"Player 1 Won",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"Player 2 Won",Toast.LENGTH_SHORT).show();
            }
            for (int i = 0;i < 3;i++){
                line[i].setBackgroundResource(R.drawable.winner);
            }
            for(int i = 0;i<3;i++){
                for(int j = 0;j<3;j++){
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }


}