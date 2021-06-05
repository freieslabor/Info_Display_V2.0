package Info_Display.V20.lib.Exception;

import Info_Display.V20.lib.Exception.RoomStatusExceptions.RoomStatusException;
import Info_Display.V20.lib.Exception.StuffInSpaceExceptions.StuffInSpaceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(StuffInSpaceException.class)
    public ResponseEntity<String> handler(StuffInSpaceException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoomStatusException.class)
    public ResponseEntity<String> handler(RoomStatusException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
