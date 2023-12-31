package algonquin.cst2335.projmy;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.projmy.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.projmy.databinding.ReceiveMessageBinding;
import algonquin.cst2335.projmy.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    private RecyclerView.Adapter myAdapter;
    private int clickpos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
        ChatMessageDAO mDAO = db.cmDAO();

        //        ArrayList<String> messages = new ArrayList<>();
        ChatRoomViewModel chatModel ;
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        chatModel.selectedMessage.observe(this, newMessage -> {

            chatModel.selectedMessage.observe(this, (newValue) -> {
                MessageDetailsFragment chatFragment = new MessageDetailsFragment(newValue);
                FragmentManager fMgr = getSupportFragmentManager();

                FragmentTransaction tx = fMgr.beginTransaction();

                tx.addToBackStack("");
                tx.replace(R.id.fragmentLocation, chatFragment);
                tx.commit();
            });

        });

        ArrayList<ChatMessage> messages;
        messages = chatModel.messages.getValue();

        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());

            Executor thread = Executors.newSingleThreadExecutor();
            ArrayList<ChatMessage> finalMessages2 = messages;
            thread.execute(() ->
            {
                finalMessages2.addAll( mDAO.getAllMessages() ); //Once you get the data from database

                runOnUiThread( () ->  binding.recycleView.setAdapter( myAdapter )); //You can then load the RecyclerView
            });
        }


        ArrayList<ChatMessage> finalMessages3 = messages;
        class MyRowHolder extends RecyclerView.ViewHolder {
            TextView messageText;
//            TextView timeText;
            ImageView imageView;

            public MyRowHolder(@NonNull View itemView) {
                super(itemView);

                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this );

                itemView.setOnLongClickListener(clk -> {
                    int position = getAbsoluteAdapterPosition();
                    builder.setMessage( "Do you want to delete the message: " + messageText.getText() )
                    .setTitle( "Question:" )
                    .setNegativeButton("No", (dialog, cl) -> { })
                    .setPositiveButton("Yes", (dialog, cl) -> {

                        ChatMessage m = finalMessages3.get(position);

                        Executor thread = Executors.newSingleThreadExecutor();
                        thread.execute( () -> {
                            mDAO.deleteMessage( m );
                            finalMessages3.remove(position);
                            runOnUiThread(() -> { myAdapter.notifyItemRemoved(position); });
                        });
                        Snackbar.make(messageText, "You deleted message #"+ position, Snackbar.LENGTH_LONG)
                                .setAction("Undo", c -> {
                                    finalMessages3.add(position, m);
                                    myAdapter.notifyItemInserted(position);
                                })
                                .show();
                })
                            .create().show();
                    return false;
                });

                itemView.setOnClickListener(clk -> {
                    int index = getAbsoluteAdapterPosition();
                    ChatMessage selectedMessage = finalMessages3.get(index);

                    chatModel.selectedMessage.postValue( finalMessages3.get(index) );

                });

                messageText = itemView.findViewById(R.id.message);
                imageView = itemView.findViewById(R.id.imageView2);
            }
        }


        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<ChatMessage> finalMessages = messages;

        binding.receiveButton.setOnClickListener(click -> {
            String input = binding.textInput.getText().toString();
            String height = binding.textInput2.getText().toString();
            boolean type = false;

            ChatMessage newMessage = new ChatMessage(input, height, type);

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                newMessage.id = mDAO.insertMessage(newMessage);
            });

            finalMessages.add(newMessage);
            myAdapter.notifyItemInserted(finalMessages.size()-1);
            binding.textInput.setText("");
            binding.textInput2.setText("");

            CharSequence text = "Image created!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
            toast.show();
        });


        ArrayList<ChatMessage> finalMessages1 = messages;
        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                    ReceiveMessageBinding binding =
                            ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder( binding.getRoot() );

            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.messageText.setText("");
                ChatMessage obj = finalMessages1.get(position);
                holder.messageText.setText(obj.getMessage() +"X"+ obj.getTimeSent() + " Bear Imaage");
                String url = obj.getUrl();
                Picasso.get().load(url).into(holder.imageView);

            }

            @Override
            public int getItemCount() {
                return finalMessages1.size();
            }

            @Override
            public int getItemViewType(int position){
                ChatMessage obj = finalMessages1.get(position);
                if (obj.getIsSentButton() == true)
                    return 1;
                else
                    return 2;
            }







        });

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));


    }


}