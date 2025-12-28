package service;

import DAO.AnalysisDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import model.entities.Analysis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisService {

    private final AnalysisDAO analysisDAO;

    public AnalysisService(AnalysisDAO analysisDAO) {
        this.analysisDAO = analysisDAO;
    }

    public Analysis create(Analysis obj) {
        if (obj == null)
            throw new RuntimeException("Empty Object");

        try {
            return analysisDAO.create(obj);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Analysis> findAll() {
        try {
            return analysisDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Analysis findById(Integer id) {
        try {
            return analysisDAO.findById(id);
        } catch (SQLException | JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Analysis> getMultipleById(List<Integer> id) {
        List<Analysis> list = new ArrayList<>();
        try {
            for (Integer analysisId : id) {
                Analysis obj = analysisDAO.findById(analysisId);
                if (list != null) {
                    list.add(obj);
                }
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Analysis obj) {
        if (obj == null || obj.getId() == null) throw new RuntimeException("Invalid object for update");
        try {
            analysisDAO.update(obj);
        } catch (SQLException | JsonProcessingException e) {
            throw new RuntimeException("Error updating analysis: " + e.getMessage());
        }
    }

    public void delete(Integer id) {
        try {
            analysisDAO.findById(id);

            analysisDAO.delete(id);
        } catch (SQLException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
