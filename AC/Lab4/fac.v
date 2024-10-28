module fac(
  input a, b ,cin,
  output s, cout
);

assign {cout, s} = a + b + cin;

endmodule

module fac_tb;
  reg a, b, cin;
  wire s, cout;
  
  fac fac_i(
    .a(a),
    .b(b),
    .cin(cin),
    .s(s),
    .cout(cout)
  );
  
  integer i;
  
  initial begin
    
    {a, b, cin} = 0; 

    $display("Time\ta\tb\tcin\ts\tcout\t");
    $monitor("%0t\t%b\t%b\t%b\t%b\t%b\t", $time, a, b, cin, s, cout);
    
    for(i=0; i<8; i=i+1)
        #10 {a, b, cin} = i;
        #20;
  end
    
endmodule