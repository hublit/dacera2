/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neg;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author depr102
 */
 public class nameFilter implements FileFilter {
		private String mask;

		nameFilter(String mask) {
			this.mask = mask;
		}

		public boolean accept(File file) {
			if (file.getName().indexOf(mask)>0) {
				return true;
			} else {
				return false;
			}

				//return file.getName().matches(mask);
		}
	}
