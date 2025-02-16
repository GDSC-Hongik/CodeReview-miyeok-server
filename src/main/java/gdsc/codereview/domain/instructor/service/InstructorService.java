package gdsc.codereview.domain.instructor.service;

import gdsc.codereview.domain.instructor.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;
}
