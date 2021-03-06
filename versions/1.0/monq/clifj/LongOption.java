/*+********************************************************************* 
This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software Foundation
Foundation, Inc., 59 Temple Place - Suite 330, Boston MA 02111-1307, USA.
************************************************************************/

package monq.clifj;

/**
 * is a command line option with <code>long</code> valued arguments.
 *
 * @author &copy; 2005 Harald Kirsch
 */
public class LongOption extends Option {
  long min;
  long max;
  public LongOption(String opt, String name, String usage,
		    int cmin, int cmax, long min, long max,
		    Object[] defalt) {
    super(opt, name, usage, cmin, cmax, defalt);
    this.min = min;
    this.max = max;
  }
  public String getTypeName() {return "long";}
  public Object check(String s) throws CommandlineException {
    long l;
    try {
      l = Long.parseLong(s);
    } catch( NumberFormatException e ) {
      throw new CommandlineException
	("option `"+opt+"' expects long value but found `"+s+"'");
    }
    if( l<min ) {
      throw new CommandlineException
	("long value "+s+" for option `"+opt+"' smaller than allowed "+min);
    }
    if( l>max ) {
      throw new CommandlineException
	("long value "+s+" for option `"+opt+"' larger than allowed "+max);
    }
    return new Long(l);
  }
  public String addRestrictions(String s) {
    if( min>Long.MIN_VALUE || max<Long.MAX_VALUE ) {
      s = s + " in the range ["+min+", "+max+"]";
    }
    return s;
  }
}
