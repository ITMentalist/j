/* 
 * The MIT License
 *
 * Copyright (c) 2017 Edoardo Luppi <lp.edoardo@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package j;

import java.util.List;
import j.enums.JType;
import j.interfaces.JDatabaseFile;
import j.interfaces.JDeviceFile;
import j.interfaces.JMember;
import j.interfaces.JObject;

import static java.util.Collections.EMPTY_LIST;
import static j.enums.JType.NONE;
import static j.misc.EmptyArrays.EMPTY_STRING;

/**
 * @author Edoardo Luppi
 */
public class JNullObject implements JDeviceFile, JDatabaseFile
{
   private static final JConnection _CONNECTION = JConnection.getInstance();
   private static JNullObject _instance = null;

   /**
    * Returns the only one ever instance of the class.
    */
   public static synchronized JNullObject getInstance() {
      if (_instance == null) {
         _instance = new JNullObject();
      }

      return _instance;
   }

   private JNullObject() {
      //
   }

   @Override
   public boolean exists() {
      return false;
   }

   @Override
   public boolean commitChanges() {
      return false;
   }

   @Override
   public boolean copy(final String library, final String name, final boolean overwrite) {
      return false;
   }

   @Override
   public boolean move(final String library, final String name, final boolean overwrite) {
      return false;
   }

   @Override
   public boolean delete() {
      return false;
   }

   @Override
   public String getPath() {
      return "";
   }

   @Override
   public String getQualifiedPath() {
      return "";
   }

   @Override
   public String getLibrary() {
      return "";
   }

   @Override
   public String getName() {
      return "";
   }

   @Override
   public JType getType() {
      return NONE;
   }

   @Override
   public String getAttribute() {
      return "";
   }

   @Override
   public String getText() {
      return "";
   }

   @Override
   public String getCreator() {
      return "";
   }

   @Override
   public long getCreationDateTime() {
      return 0;
   }

   @Override
   public void setName(final String name) {
      //
   }

   @Override
   public void setText(final String text) {
      //
   }

   @Override
   public List<JRecordFormat> getRecordFormats() {
      return EMPTY_LIST;
   }

   @Override
   public String[] getMembers() {
      return EMPTY_STRING;
   }

   @Override
   public List<JObject> getDatabaseRelations() {
      return EMPTY_LIST;
   }

   @Override
   public JJournal getJournal() {
      throw new UnsupportedOperationException();
   }

   @Override
   public JMember toSql(final String library, final String object, final String member, final String type) {
      throw new UnsupportedOperationException();
   }

   @Override
   public JConnection getConnection() {
      return _CONNECTION;
   }
}