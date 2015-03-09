package hello;



/**
 * Created by Lenovo on 2/27/2015.
 */

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//import org.neo4j.cypher.internal.compiler.v2_1.perty.impl.quoteString;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
//@RequestMapping( consumes = {"application/json","application/xml"}, produces = {"application/json","application/xml"})
public class Modaretor
{


    HashMap<String , ModaretorData> moderatorHashMap = new HashMap<String, ModaretorData>();
    @RequestMapping(value = "/api/v1/moderators", method = {RequestMethod.POST},
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<ModaretorData> createModerator(@RequestBody @Valid ModaretorData moderator)
    {
        moderatorHashMap.put(""+moderator.getId(), moderator);
        return new ResponseEntity<ModaretorData>( moderatorHashMap.get("" + moderator.getId()), HttpStatus.CREATED);
    }




    @RequestMapping(value = "api/v1/moderators/{m_id}", method =RequestMethod.PUT)

    public ResponseEntity<ModaretorData> updateModerator( @PathVariable("m_id") int m_id,
                                         @RequestBody @Valid ModaretorData moderator) {

        moderator.resetCounter();


            ModaretorData tempMod=moderatorHashMap.get(""+m_id);


            tempMod=moderatorHashMap.get(""+m_id);
            if(!(moderator.getEmail()==null))
            {tempMod.setEmail(moderator.getEmail());}
            if(!(moderator.getPassword()==null))
            {tempMod.setPassword(moderator.getPassword());}
            if(!(moderator.getName()==null))
            {tempMod.setName(moderator.getName());}
            moderatorHashMap.remove(""+m_id);
            moderatorHashMap.put(""+m_id,tempMod);


        return new ResponseEntity<ModaretorData>( moderatorHashMap.get("" + m_id), HttpStatus.CREATED);


    }

    @RequestMapping(value = "api/v1/moderators/{m_id}", method = RequestMethod.GET)

    public ResponseEntity<ModaretorData> getModerator(HttpServletRequest request, @PathVariable("m_id") int m_id
                                         ) {



         return new ResponseEntity<ModaretorData>( moderatorHashMap.get("" + m_id), HttpStatus.CREATED);


    }






}





