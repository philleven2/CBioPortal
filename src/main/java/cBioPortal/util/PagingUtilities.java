package cBioPortal.util;

import java.util.ArrayList;

/**
 * @author LevenP
 *
 */
public class PagingUtilities {

  /**
   * calculate from/to rows
   * 
   * @param pagSiz
   * @param mv
   * @param firstRow
   * @param fromRow
   * @param toRow
   * @return
   */
  public static ArrayList<String> calcFromToRows(int pagSiz, String mv, String firstRow, String fromRow, String toRow) {

    ArrayList<String> alist2 = new ArrayList<String>(2);
    int fRow = 0;
    int tRow = 0;

    // If update or back
    if (firstRow != null) {

      fromRow = firstRow;

    }

    // If fromRow
    if (fromRow != null) {

      fRow = Integer.parseInt(fromRow);

    } else {

      fRow = 0;
    }

    // If toRow
    if (toRow != null) {

      tRow = Integer.parseInt(toRow);

    }

    // Calculate new fromRow and toRow
    // If page down
    if (mv.equals("PageDown")) {

      fRow = tRow + 1;
      tRow = fRow + pagSiz - 1;

      // If page up
    } else if (mv.equals("PageUp")) {

      fRow = fRow - pagSiz;
      tRow = fRow + pagSiz - 1;

      // If 1st time, update or back
    } else {

      tRow = fRow + pagSiz - 1;

    }

    // Add fRow to ArrayList
    alist2.add(String.valueOf(fRow));

    // Add tRow to ArrayList
    alist2.add(String.valueOf(tRow));

    return alist2;

  }

}
