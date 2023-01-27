sub tailor()
   # В бекпак ложим ножницы (scissors) и набор для шитья (sewing kit)
   # Если тейлора меньше 60, то шьем рубашки и кидаем на пол
   # Если 60 и больше шьем робы и режем на бинты, бинты кидаем в сумку
   # Нужно указать ID сумки
   VAR sumka = '0x522B7F85'
   # Ниже ничего не трогаем
   VAR sw = 0, i
   VAR clothes = '0x1765'
   VAR sewingKit = '0x0F9D'
   UO.Say('.macro off')
   uo.set('finddistance','2')
   while true
      if UO.SkillVal('Tailoring') < 600 then
         if sw == 0 then
            sw = 1
            UO.CancelMenu()
            UO.AutoMenu("Choose a category.", "shirts")
            UO.AutoMenu("What kind of shirt?", "Fancy Shirt")
         else
            UO.Findtype(clothes, '-1', 'ground')
            If UO.GetQuantity('finditem') >= 8 Then
               UO.WaitTargetObject('finditem')
               UO.UseType(sewingKit)
               wait(9900)
               UO.Findtype('0x1EFD', '-1', 'backpack')
               If UO.FindCount() > 0 Then
                  UO.MoveItem('finditem', 1, 'ground')
                  wait(10)
               endif
            else
               UO.charprint(UO.GetSerial(), '172','Ткань кончилась')
               return
            endif
         endif
      endif
      if UO.SkillVal('Tailoring') >= 600 then
         if sw == 0 or sw == 1 then
            sw = 2
            UO.CancelMenu()
            UO.AutoMenu("Choose a category.", "robes")
            UO.AutoMenu("What robe or cloak?", "Robe")
         else
            UO.Findtype(clothes, '-1', 'ground')
            If UO.GetQuantity('finditem') >= 16 Then
               UO.WaitTargetObject('finditem')
               UO.UseType(sewingKit)
               wait(10000)
               for i = 0 to 5
                  UO.Findtype('0x1F03', '-1', 'backpack')
                  If UO.FindCount() > 0 Then
                     UO.WaitTargetObject('finditem')
                     UO.UseType('0x0F9E')
                     wait(5500)
                  else
                     i = 5  
                  endif
               next
               UO.Findtype('0x0E21', '-1', 'backpack')
               If UO.FindCount() > 0 Then
                  UO.MoveItem('finditem', '0', sumka)
                  wait(10)
               endif
            else
               UO.charprint(UO.GetSerial(), '172','Ткань кончилась')
               return
            endif
         endif
      endif
      if UO.SkillVal('Tailoring') == 1000 then
         return
      endif 
   wend
end sub