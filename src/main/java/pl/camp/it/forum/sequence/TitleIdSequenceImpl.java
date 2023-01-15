package pl.camp.it.forum.sequence;

import org.springframework.stereotype.Component;

@Component
public class TitleIdSequenceImpl implements ITitleIdSequence {

    private int id = 0;

    @Override
    public int getId() {
        return ++id;
    }
}
