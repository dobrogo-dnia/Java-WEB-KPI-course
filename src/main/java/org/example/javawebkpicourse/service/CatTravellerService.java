package org.example.javawebkpicourse.service;

import org.example.javawebkpicourse.domain.CatTravellerDetails;
import java.util.List;

public interface CatTravellerService {

    List<CatTravellerDetails> getAllCatTravellerDetails();

    CatTravellerDetails getCatTravellerDetailsById(Long travellerId);

}
