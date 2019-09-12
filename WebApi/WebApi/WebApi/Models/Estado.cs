using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class Estado
    {
        public Estado()
        {
            Cidade = new HashSet<Cidade>();
        }

       
        public int CdEstado { get; set; }
        public string NmEstado { get; set; }
        public string SgEstado { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public ICollection<Cidade> Cidade { get; set; }
    }
}
