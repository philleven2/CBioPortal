package cBioPortal.service;

import java.sql.Connection;
import java.util.List;

import cBioPortal.beans.Logs;

public interface LogsService {

  public int getCount(Connection conn, String schText);

  public List<Logs> getLogs(Connection conn, int fRow, int pagSiz, String schText);

}