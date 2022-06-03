package eway.file;

import eway.model.Area;

import java.util.List;

public interface FileHandle {
    List<Area> readFile(String filePath);
}
