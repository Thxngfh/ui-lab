# UI lab results

Tool parts that I chose: Part 2: Selenium WebDriver in Java and Part 4: Cypress

## Part 0

- Step 0.1, Chrome version: 157.0.8103.79 (Official Build) (arm64)
- Step 0.3, ChromeDriver version: 157.0.8103.78
- Step 0.4, locator of Pizza, Please!: `name=pizza-please`
- Step 0.4, locator of Excluded tools: `css=select`
- Step 0.4, locator of Must be vegetarian: `id=default-checkbox`
- Step 0.4, locator of Custom Pizza Name: `css=input#pizza-name`
- Step 0.5, pizza name, tool and calories of the run by hand: Lab Pizza 20261115-195536, Scissors, 400 calories per slice

| Step | Actual result | Pass or fail |
|------|---------------|--------------|
| 1 | The heading reads "Looking to break out of your pizza routine?" | Pass |
| 2 | Max Calories per Slice, Excluded tools, Must be vegetarian and Custom Pizza Name appeared | Pass |
| 3 | Max Calories was 600, Knife was excluded, vegetarian was ticked, and the pizza name was Lab Pizza 20261115-195536 | Pass |
| 4 | The recommendation showed Lab Pizza 20261115-195536, tool Scissors, and 400 calories per slice | Pass |
| 5 | The page showed "Please log in first." | Pass |
| 6 | The admin page showed "Latest pizza recommendations" after signing in | Pass |
| 7 | The admin list showed Lab Pizza 20261115-195536 with tool Scissors | Pass |

## Part 2: Selenium WebDriver in Java

- Step 2.4, the failure line for the wrong title: FirstTest.webFormOpens:19 expected: <Web Form> but was: <Web form>
- Step 2.15, QP01Test run 1, result and time: BUILD SUCCESS, 15.23 seconds
- Step 2.15, QP01Test run 2, result and time: BUILD SUCCESS, 20.40 seconds
- Step 2.20, full run 1, time and number of tests: BUILD SUCCESS, 47.118 seconds, 5 tests passed
- Step 2.20, full run 2, time and number of tests: BUILD SUCCESS, 42.185 seconds, 5 tests passed

## Part 4: Cypress

- node --version, npm --version and npx cypress version: v22.20.0, 10.9.3, Cypress 16.2.1
- Step 4.3, failure message for the wrong title: Timed out retrying after 10000ms, expected Web Form but was Web form
- Step 4.6, tool and calories in the answer of POST /api/pizza: Tool = Scissors, Calories = 450
- Step 4.7, the line of the admin list that shows your pizza: Lab Pizza 20261115-214508 (tool=Pizza cutter, ingredients_number=8)
- Step 4.8, run 1, result, duration and pizza name: PASS, 21 seconds, [pizza name]
- Step 4.8, run 2, result, duration and pizza name: PASS, 18 seconds, [pizza name]

## Part B

1.Explain why the tests locate Max Calories per Slice through its label instead of its id. Propose a change to the page that would let a test use an id, and state why that change helps a tester.
ANS ที่ต้องใช้ Label ในการค้นหาช่อง Max Calories per Slice เพราะหน้าเว็บมี Input หลายช่องที่ใช้ id เดียวกันคือ floating_filled ทำให้ถ้าใช้ id อย่างเดียวอาจเลือกช่องผิดได้ จึงต้องใช้ข้อความ Label เพื่อระบุว่าต้องการช่องไหนโดยเฉพาะ ถ้าสามารถแก้หน้าเว็บได้ ควรกำหนด id ให้แต่ละช่องไม่ซ้ำกัน เช่น max-calories จะช่วยให้เขียน Test ได้ง่ายขึ้นและลดโอกาสเกิดข้อผิดพลาด

2.Step 7 of QP-01 compares the tool in the admin list with the tool in the recommendation. Identify the kind of test oracle that this check is, and describe one fault in QuickPizza that it would catch.
ANS Step 7 เป็นการตรวจสอบแบบ Consistency Check เพราะเป็นการเปรียบเทียบข้อมูล Tool ที่แสดงในหน้า Recommendation กับข้อมูล Tool ในหน้า Admin ว่าตรงกันหรือไม่ เช่น ถ้าหน้า Recommendation แสดงว่าใช้ Scissors แต่หน้า Admin กลับแสดงว่าใช้ Knife แสดงว่าข้อมูลของระบบไม่ตรงกัน การตรวจสอบนี้ช่วยให้เราพบข้อผิดพลาดในการแสดงข้อมูลของ Pizza ได้ 

3.QP-01 never checks that the pizza is vegetarian. Propose a check that would, and state what the test would need from the page or from another source.
ANS ใน QP-01 ยังไม่ได้ตรวจสอบโดยตรงว่า Pizza ที่ได้เป็นอาหารมังสวิรัติจริงหรือไม่ ถึงแม้เราจะติ๊ก Must be vegetarian ไว้แล้วก็ตาม สามารถเพิ่มการตรวจสอบโดยดูค่า vegetarian ใน API Response ว่าต้องเป็น true หรือไม่ นอกจากนี้ยังสามารถตรวจสอบส่วนผสมแต่ละอย่างได้ด้วย โดยต้องมีข้อมูลจาก API หรือแหล่งข้อมูลที่บอกว่าส่วนผสมแต่ละชนิดเป็นมังสวิรัติหรือไม่

4.Compare the two tools that you used. Describe how each one waited for QuickPizza, how quickly you could write QP-01 with it, and how readable its report, log or video was.
ANS Selenium WebDriver ใน Java ใช้ Explicit Wait เช่น WebDriverWait และ ExpectedConditions เพื่อรอให้ Element ปรากฏก่อนทำงาน ส่วน Cypress จะรอและลองตรวจสอบ Element ซ้ำให้อัตโนมัติ ทำให้ไม่ต้องเขียนคำสั่งรอเองทุกครั้ง ตอนทำงานรู้สึกว่า Selenium Java ต้องเขียนโค้ดและตั้งค่าหลายส่วนมากกว่า แต่มี Page Object ช่วยจัดระเบียบโค้ด ส่วน Cypress เขียน Test ได้ค่อนข้างง่าย และมี Command Log, Screenshot และ Video ที่ช่วยให้ดูผลการทดสอบและหาข้อผิดพลาดได้สะดวก 

5.Choose the tool that you would use for the automated UI tests of Project 2, and justify the choice with two reasons from this lab.
ANS ถ้าต้องเลือกเครื่องมือไปใช้ทำ Automated UI Testing ใน Project 2 ผมเลือก Cypress เพราะใช้งานง่ายและเขียน Test ได้ไม่ซับซ้อนมาก อีกทั้งยังมีระบบรอ Element อัตโนมัติ ทำให้ลดปัญหาการทำงานไม่ทันตอนหน้าเว็บโหลด นอกจากนี้ยังสามารถใช้ cy.intercept() เพื่อตรวจสอบข้อมูลที่ส่งไปยัง API และข้อมูลที่ Server ส่งกลับมาได้ ทำให้ตรวจสอบการทำงานของระบบได้ละเอียดมากขึ้น

6.For Part 2, explain what has to change in QP01Test and in QP01PageObjectTest if QuickPizza changes the locator of the calories box, and why the page objects make that repair smaller.
ANS ถ้า QuickPizza เปลี่ยน Locator ของช่อง Max Calories จะต้องแก้ไขในไฟล์ QP01Test.java เพราะมีการเขียน XPath ไว้โดยตรง ส่วน QP01PageObjectTest.java จะเรียกใช้งานผ่าน Method ใน HomePage.java ดังนั้นสามารถแก้ Locator ได้ที่ HomePage.java เพียงไฟล์เดียว โดยไม่ต้องไปแก้ Test ทุกจุด วิธีนี้ช่วยให้ดูแลและแก้ไขโค้ดได้ง่ายขึ้น เพราะแยกส่วนของ Locator ออกจากส่วนของ Test

7.For Part 4, explain why QP-01 has to be a single test in Cypress, which clears the cookies before each test, and what would go wrong if steps 6 and 7 were a second test.
ANS QP-01 ควรเขียนให้อยู่ใน Test เดียวกัน เพราะ Cypress จะล้าง Cookies ก่อนเริ่ม Test ใหม่ ซึ่ง QuickPizza ใช้ Cookies ในการจดจำว่า Browser เชื่อมต่อกับ Server ตัวไหนอยู่ ถ้าแยก Step 6 และ Step 7 ออกเป็นอีก Test หนึ่ง Cookies อาจถูกล้างและทำให้ Browser ไปเชื่อมต่อกับ Server คนละตัวได้ ส่งผลให้หน้า Admin อาจไม่พบ Pizza ที่สร้างไว้ในขั้นตอนก่อนหน้า และทำให้ Test ไม่ผ่าน