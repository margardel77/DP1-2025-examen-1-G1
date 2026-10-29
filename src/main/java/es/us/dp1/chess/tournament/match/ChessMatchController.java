package es.us.dp1.chess.tournament.match;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.us.dp1.chess.tournament.exceptions.AccessDeniedException;
import es.us.dp1.chess.tournament.exceptions.ResourceNotFoundException;
import es.us.dp1.chess.tournament.user.User;
import es.us.dp1.chess.tournament.user.UserService;


@RestController 
@RequestMapping ("api/v1/matches")
public class ChessMatchController {
    @Autowired 
    UserService userService;
    @Autowired 
    ChessMatchService matchService;


    public ChessMatchController(ChessMatchService service,UserService userService){
        this.matchService=service;
        this.userService=userService;    
    }
    @GetMapping 
    public ResponseEntity<List<ChessMatch>>getAllMatches(){
        return new ResponseEntity<>(matchService.getMatches(),HttpStatus.OK);
    }

    @GetMapping ("{id}")
    public ResponseEntity<ChessMatch> getMatchById(@PathVariable Integer id){
        ChessMatch cm = matchService.getMatchById(id).orElseThrow(()->new ResourceNotFoundException(null));
        User u = userService.findCurrentUser();
        if(u.getAuthority().toString() == "PLAYER") {
            throw new AccessDeniedException();
        }
       return new ResponseEntity<>(cm,HttpStatus.OK);
    }

}