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
package lppedd.j;

import lppedd.j.abstracts.JAbstractMember;
import lppedd.j.exceptions.JInvalidWorkItemException;
import smi.workitem.SMISqlWorkItem;
import smi.workitem.abstracts.SMIWorkItem;

import static java.lang.Long.parseLong;
import static lppedd.j.JObjectFactory.get;
import static lppedd.j.enums.JType.FILE;
import static lppedd.misc.Util.isInteger;

/**
 * Represents an IBMi SQL source member.
 *
 * @author Edoardo Luppi
 */
public class JSQLMember extends JAbstractMember
{
   public JSQLMember(final String name, final String object, final String library) {
      super(name, object, library);
   }

   @Override
   public boolean compile(final String library) {
      final long time = parseLong(getConnection().getSystemValue("QDATETIME").toString().substring(0, 14) + "000");

      final StringBuilder builder = new StringBuilder(92);
      builder.append("ABCRTSQL SRCFILE(");
      builder.append(_library);
      builder.append("/");
      builder.append(_object);
      builder.append(") MEM(");
      builder.append(_name);
      builder.append(") OBJ(");
      builder.append(library);
      builder.append("/");
      builder.append(_name);
      builder.append(")");

      getConnection().executeCommand(builder.toString());
      return time <= get(_name, library, FILE).getCreationDateTime();
   }

   @Override
   protected void inspectForWorkItems() {
      // Se necessario pulisco la lista prima di popolarla
      if (_workItemList.size() > 0) {
         _workItemList.clear();
      }

      for (int i = 0; i < _source.size(); i++) {
         final String line = _source.get(i);

         // Sono alla fine dei work item in testatina? Se si, salvo la posizione
         if (!_workItemList.isEmpty() && PATTERN_EOC.matcher(line).matches()) {
            _workItemsEnd = i;
            break;
         }

         // Se la linea corrisponde ad un un work item, lo aggiungo alla lista
         // e mi salvo la posizioni in modo da poter, successivamente, andare ad inserire il suo testo
         if (PATTERN_MOD.matcher(line).matches() && isInteger(line.substring(16, 24))) {
            try {
               _workItemList.add(new SMISqlWorkItem(this, line), i);
            } catch (final JInvalidWorkItemException e) {
               e.printStackTrace();
            }
         }
      }

      // Completo i work item con i rispettivi testi
      final int size = _workItemList.size() - 1;

      for (int i = 0; i <= size; i++) {
         final SMIWorkItem workItem = _workItemList.getWorkItem(i);

         for (int k = _workItemList.getPosition(i) + 1; k < (i == size ? _workItemsEnd : _workItemList.getPosition(i + 1)); k++) {
            workItem.appendText(_source.get(k).substring(9));
         }
      }
   }

   @Override
   protected String backToThePast(final String line, final String modNumber) {
      // TODO
      return line;
   }
}
