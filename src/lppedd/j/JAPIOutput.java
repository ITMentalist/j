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

import com.ibm.as400.access.AS400Message;

import static lppedd.misc.EmptyArrays.EMPTY_BYTE;

/**
 * Represents the output value of an IBMi API call.
 *
 * @author Edoardo Luppi
 */
public class JAPIOutput
{
   private final byte[] _value;
   private final AS400Message[] _messages;

   public JAPIOutput(final byte[] value, final AS400Message[] messages) {
      _value = value;
      _messages = messages;
   }

   public JAPIOutput(final byte[] value) {
      _value = value;
      _messages = new AS400Message[0];
   }

   public JAPIOutput(final byte[] value, final AS400Message message) {
      this(value, new AS400Message[] {
         message
      });
   }

   public JAPIOutput(final AS400Message[] messages) {
      this(new byte[0], messages);
   }

   public JAPIOutput(final AS400Message message) {
      this(EMPTY_BYTE, message);
   }

   public byte[] getValue() {
      return _value;
   }

   public AS400Message[] getMessages() {
      return _messages;
   }
}
