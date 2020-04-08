// ========================================================================== ;
//                                                                            ;
//     Copyright (96)  Hartmut S. Loos                                        ;
//                                                                            ;
//     Institut f"ur Neuroinformatik   ND 03                                  ;
//     Ruhr-Universit"at Bochum                                               ;
//     44780 Bochum                                                           ;
//                                                                            ;
//     Tel  : +49 234 7007970                                                 ;
//     Email: loos@neuroinformatik.ruhr-uni-bochum.de                         ;
//                                                                            ;
// ========================================================================== ;
//                                                                            ;
// Copyright 1996 Hartmut S. Loos                                             ;
//                                                                            ;
// This program is free software; you can redistribute it and/or modify       ;
// it under the terms of the GNU General Public License as published by       ;
// the Free Software Foundation; either version 1, or (at your option)        ;
// any later version.                                                         ;
//                                                                            ;
// This program is distributed in the hope that it will be useful,            ;
// but WITHOUT ANY WARRANTY; without even the implied warranty of             ;
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              ;
// GNU General Public License for more details.                               ;
//                                                                            ;
// You should have received a copy of the GNU General Public License          ;
// along with this program; if not, write to the Free Software                ;
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.                  ;
//                                                                            ;
// ========================================================================== ;

/**
 * A class representing a double point in the plane.
 *
 */
class DPoint {
  /**
   * The x coordinate
   */
  public double x;
  /**
   * The y coordinate
   */
  public double y;

  /**
   * Constructor.
   * 
   */
  public DPoint() {
    this.x = -1.0f;
    this.y = -1.0f;
  }

  /**
   * Constructor, allows setting the coordinates.
   * 
   * @param x        The x coordinate
   * @param y        The y coordinate
   */
  public DPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Constructor, allows setting the coordinates.
   * 
   * @param p        Another DPoint
   */
  public DPoint(DPoint p) {
    this.x = p.x;
    this.y = p.y;
  }

  /**
   * Set the member variables.
   * 
   * @param x        The x coordinate
   * @param y        The y coordinate
   */
  public void set(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Set the member variables.
   * 
   * @param p        The coordinates
   */
  public void set(DPoint p) {
    x = p.x;
    y = p.y;
  }

  /**
   * Test the member variables.
   * 
   * @param x        The x coordinate
   * @param y        The y coordinate
   * @return	     Equal?
   */
  public boolean equal(double x, double y) {
    if ( (this.x == x) && (this.y == y) ) {
      return(true);
    } else {
      return(false);
    }
  }

}
