package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.chess.tournament.exceptions.ResourceNotFoundException;
@Service 
public class PieceService {

    PieceRepository repository;

    public PieceService(Optional<PieceRepository> repository) {
        this.repository = repository.isPresent()?repository.get():null;
    }
    @Transactional 
    public List<Piece> getPiecesByColorAndType(PieceColor color, String typeName) {
        // TODO: implement this method to solve Exercise 2a
        return repository.findByColorAndTypeName(color, typeName);
    }
    @Transactional
    public List<Piece> getAll() {
        // TODO: implement this method to solve Exercise 2a
        return repository.findAll();
    }
    @Transactional
    public Piece getById(Integer i) {
        // TODO: implement this method to solve Exercise 2a
        return repository.findById(i).orElseThrow(() -> new ResourceNotFoundException(null));
    }
    @Transactional
    public Piece save(Piece piece) {
        // TODO: implement this method to solve Exercise 2a
        return repository.save(piece);
    }
    
}
