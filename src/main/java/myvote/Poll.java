package hello;

/**
 * Created by Lenovo on 3/4/2015.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
public class Poll
{

    HashMap<String , PollData> pollHashMap = new HashMap<String, PollData>();
    HashMap<String , ModPoll> pollModHashMap = new HashMap<String, ModPoll>();
    HashMap<String , Integer []> pollReultHashMap = new HashMap<String, Integer []>();

    public class ListAllPolls
    {
        public PollData poll[];
        public Integer reults[][];
    }

    public class ViewWithResults
    {
        public PollData poll;
        public Integer reults[];
        public ViewWithResults(){};
    }




    @RequestMapping(value = "api/v1/moderators/{id}/polls",method = {RequestMethod.POST     }

    )
    @ResponseBody
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public PollData createPoll
            (HttpServletRequest request,@PathVariable int id,@Valid @RequestBody PollData pollData) {


        String[] choices=pollData.getChoice();
        Integer[] i=new Integer [choices.length];
        int ii []=new int [choices.length];
        for(int j=0;j<choices.length;j++)
        {
            i[j]=ii[j];
        }
        PollData myPoll=new PollData(pollData.getQuestion(), pollData.getChoice());

        pollHashMap.put(pollData.getId(), pollData);
        pollReultHashMap.put(pollData.getId(), i);
        ModPoll temp=new ModPoll();
        temp.pid=pollData.getId();
        temp.mid=id;
        pollModHashMap.put(temp.pid,temp);
        return pollHashMap.get(pollData.getId());

    }


    @RequestMapping(value = "api/v1/polls/{p_id}", method ={ RequestMethod.GET})
    public PollData viewWithOutReult(@PathVariable("p_id") String p_id) {

        return pollHashMap.get(p_id);
    }

    @RequestMapping(value = "api/v1/moderators/{mod_id}/polls/{p_id}", method ={RequestMethod.PUT,RequestMethod.GET})
    public ViewWithResults ViewWithResults(@PathVariable("p_id") String p_id,@PathVariable("mod_id") String mod_id)
    {

        ViewWithResults temp=new ViewWithResults();
        temp.poll=pollHashMap.get(p_id);
        temp.reults=pollReultHashMap.get(p_id);
        return temp;
    }
    /*
    @RequestMapping(value = "api/v1/moderators/{id}/polls",method ={RequestMethod.GET})
    public ArrayList<ViewWithResults> listAllPolls(@PathVariable("id") int id) {
        return listview(pollModHashMap, id, pollHashMap);
    }
    */
    @RequestMapping(value = "api/v1/moderators/{moderator_id}/polls/{poll_id}",method ={RequestMethod.DELETE})
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("poll_id") String poll_id) {
        pollHashMap.remove(poll_id);
        return ;
    }


    @RequestMapping(value = "api/v1/polls/{poll_id}", method ={RequestMethod.PUT})
    public void VoteAPoll(@PathVariable("poll_id") String poll_id
            ,@RequestParam(value="choice", defaultValue="-1") String choice)
    {

        Integer temp[]=pollReultHashMap.get(poll_id);
        int cho=Integer.parseInt(choice);
        temp[cho]++;
        pollReultHashMap.put(poll_id,temp);
    }



}