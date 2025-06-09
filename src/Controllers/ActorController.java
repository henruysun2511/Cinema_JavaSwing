package Controllers;

import java.util.ArrayList;

import java.util.List;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import Daos.*;
import Models.Actor;

public class ActorController {
	public static List<Actor> findActorsByMovieId(String movie_id) {
		return ActorDao.findActorsByMovieId(movie_id);
	}

}
