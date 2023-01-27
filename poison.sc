# version 2.0
# ����� ������� ��� �� 1 ������ (��� ��������), ��� � �� 14 �����.
# ��������� 2 ����� ���� � ������� ��������, ������ ��� ���������� �����.
# ����� �� ����, ��� ������ �� ���, ������� �������� � ��� ������.
# ��������� poisonArrow() ��� ����� � poisonBolts() ��� ������.
# � ������ �������� ������ �����.
VAR arrowCount = 14        # ������� ����� ���������
VAR lagWait = 500          # ��� ����� ������ 1500, ���� ����� ��� 500
# ���� ������ �� �������

VAR arrow = '', emptyBottle = '0x0F0E', poison = '0x0F0A', arrowID, sunduk, sumka, keg

sub poisonArrow()
   arrow = '0x0F3F'
   poison(arrow)
endsub

sub poisonBolts()
   arrow = '0x1BFB'
   poison(arrow)
endsub

sub poison(arrow)
   UO.charprint(UO.GetSerial(), '172','������ ����� � ������� ��������')
   UO.exec('addobject sunduk')
   while uo.targeting() 
      wait(500) 
   wend
   
   UO.charprint(UO.GetSerial(), '172','������ ����� ��� ���������� �����')
   UO.exec('addobject sumka')
   while uo.targeting() 
      wait(500) 
   wend
   
   UO.charprint(UO.GetSerial(), '172','������ ���')
   UO.exec('addobject keg')
   while uo.targeting() 
      wait(500) 
   wend
   
   sunduk = UO.GetSerial('sunduk')
   sumka = UO.GetSerial('sumka')
   keg = UO.GetSerial('keg')
   
   UO.UseObject(sunduk)
   wait(500)
   UO.UseObject(sumka)
   wait(500)
   UO.Set('finddistance','2')
   
   While true
      if UO.GetHP()<80 then 
         UO.Waittargetobject('self')
         UO.UseFromGround('0x0E21')
         wait(3500)
      else
         if UO.count( arrow ) == 0 then
            UO.FindType( arrow ,-1, sunduk)
            UO.MoveItem( 'finditem', arrowCount, 'backpack')
            wait(lagWait)
         endif
         if UO.Count( arrow ) == arrowCount then
            UO.DeleteJournal()
            UO.FindType(arrow,-1,'backpack')
            arrowID = UO.GetSerial('finditem')
            if UO.Count(emptyBottle) > 0 then
               UO.FindType(emptyBottle,-1,'backpack')
               repeat
                  UO.MoveItem( 'finditem', 0, keg)
                  wait(650)
               until UO.InJournal('now:|is out')
            else
               UO.FindType(poison,-1,'backpack')
               if UO.Count(poison) > 0 then
                  UO.WaitTargetObject('finditem', arrowID)
                  UO.UseSkill('Poisoning')
                  wait(8000)
                  If UO.Count( arrow ) > 0 AND not UO.InJournal('You fail') then
                     UO.FindType( arrow ,'0x0000', -1 )
                     UO.MoveItem( 'finditem', 0, sunduk)
                     wait(lagWait)
                  Endif
                  If UO.Count( arrow ) > 0 then
                     UO.FindType( arrow ,'0x0044', -1 )
                     UO.MoveItem( 'finditem', 0, sumka)
                     wait(lagWait)
                  Endif
               else
                  UO.charprint(UO.GetSerial(), '172','��� ������ ������� � �������')
                  return
               endif 
            endif 
         else
            if UO.count( arrow ) > 0 then
               UO.FindType( arrow ,-1, 'backpack')
               UO.MoveItem( 'finditem', 0, sunduk)
               wait(lagWait)
            endif   
         endif
         UO.FindType( '0x0E20' ,'0x0000', -1 )
         UO.MoveItem( 'finditem', 0, sumka)
         wait(lagWait)   
      endif   
   wend
endsub